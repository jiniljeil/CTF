import Head from 'next/head';
import { useRouter } from 'next/router';
import style from '../../styles/Home.module.css';
import { type FormEvent, useEffect } from 'react';

export default function Register() {
    useEffect(() => {});

    const router = useRouter();

    async function onSubmit(event: FormEvent<HTMLFormElement>) {
        event.preventDefault();
        const formData = new FormData(event.currentTarget);
        const response = await fetch('/api/auth/register', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(Object.fromEntries(formData)),
        });

        if (response.status !== 200) {
            alert('maybe username has already been used');
        } else {
            alert('registered!');
            router.push('/api/auth/signin');
        }
    }

    return (
        <>
            <Head>
                <title>hhhhhhhref register page</title>
            </Head>
            <main className={style.main}>
                <h1>hhhhhhhref register page</h1>

                <form onSubmit={onSubmit}>
                    <input type="text" name="name" placeholder="name" />
                    <input
                        type="password"
                        name="password"
                        placeholder="password"
                    />
                    <button type="submit">Register</button>
                </form>
            </main>
        </>
    );
}
