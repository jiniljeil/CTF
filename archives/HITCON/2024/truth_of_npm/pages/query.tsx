import { Context } from 'hono'
import { Page } from './page.tsx'
import * as path from 'path'
import * as fs from 'fs'
import { asyncMapToArray } from '../utils.ts'

const denoDir = Deno.env.get('DENO_DIR')
if (!denoDir) {
	throw new Error('DENO_DIR environment variable not found')
}
const npmDir = path.join(denoDir, 'npm')

type PackageJson = {
	name: string
	version: string
	description: string
}

type CachedPackageQueryResult = {
	size: number
	pkgjson: PackageJson
}

async function* walkPackageFiles(npmDir: string) {
	for await (const entry of fs.walk(npmDir)) {
		if (entry.isDirectory) continue
		// registry.json is generated by deno
		if (entry.name !== 'registry.json') {
			yield entry
		}
	}
}

export const cache = new Map<string, CachedPackageQueryResult>()

async function queryPackage(packageName: string) {
	if (cache.has(packageName)) {
		return cache.get(packageName) as CachedPackageQueryResult
	}
	const pkgjson: PackageJson | null = await (async () => {
		try {
			const module = await import(`npm:${packageName}/package.json`, {
				with: {
					type: 'json'
				}
			})
			return module.default
		} catch {
			return null
		}
	})()
	if (!pkgjson) {
		const ps = await asyncMapToArray(walkPackageFiles(npmDir), entry => Deno.remove(entry.path))
		await Promise.all(ps)
		return null
	}
	let totalSize = 0
	const ps = await asyncMapToArray(walkPackageFiles(npmDir), async entry => {
		const { size } = await Deno.stat(entry.path)
		totalSize += size
		return Deno.remove(entry.path)
	})
	await Promise.all(ps)
	const ret = { size: totalSize, pkgjson }
	cache.set(packageName, ret)
	return ret
}

const sizeFormatter = new Intl.NumberFormat('en-US', {
	style: 'decimal',
	maximumFractionDigits: 2
})

const packageNameRegex = /^(@[a-z0-9-~][a-z0-9-._~]*\/)?[a-z0-9-~][a-z0-9-._~]*$/

export const handler = async (c: Context) => {
	const packageName = await c.req.query('package')
	if (!packageName || !packageNameRegex.test(packageName)) {
		return c.json({ error: 'Invalid package name' }, 400)
	}
	const ret = await queryPackage(packageName)
	if (!ret) {
		return c.html(
			<Page title={`Package details for ${packageName}`}>
				<div class="container">
					<div class="p-5 mb-4 bg-light rounded-3">
						<div class="container-fluid py-5">
							<h1 class="display-5 fw-bold">{`Package details for ${packageName}`}</h1>
							<p class="col-md-8 fs-4">Package not found or failed to install</p>
						</div>
					</div>
				</div>
			</Page>
		)
	}
	const { size, pkgjson } = ret
	return c.html(
		<Page title={`Package details for ${packageName}`}>
			<div class="container">
				<div class="p-5 mb-4 bg-light rounded-3">
					<div class="container-fluid py-5">
						<h1 class="display-5 fw-bold">{`Package details for ${packageName}`}</h1>
						<p class="col-md-8 fs-4">{pkgjson.description}</p>
						<div>
							<ul>
								<li>Version: {pkgjson.version}</li>
								<li>Size: {sizeFormatter.format(size / 1024)} KB</li>
							</ul>
						</div>
					</div>
				</div>
				<div>
					<h2>package.json</h2>
					<pre>{JSON.stringify(pkgjson, null, 2)}</pre>
				</div>
			</div>
		</Page>
	)
}