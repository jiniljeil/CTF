import Head from 'next/head';
import { useRouter } from 'next/router';
import style from '../../styles/Home.module.css';
import { type FormEvent } from 'react';

export default function Crawl() {
    const router = useRouter();

    async function onSubmit(event: FormEvent<HTMLFormElement>) {
        event.preventDefault();
        const formData = new FormData(event.currentTarget);
        const jsonData = JSON.stringify(Object.fromEntries(formData));
        const response = await fetch('/api/bot/crawl', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: jsonData,
        });

        if (response.status !== 200) {
            alert('something went wrong!');
        } else {
            alert('The crawler will run with provided credentials!');
            router.push('/');
        }
    }

    return (
        <>
            <Head>hhhhhhhref crawler page</Head>
            <main className={style.main}>
                <h1>hhhhhhhref crawler page</h1>

                <div className={style.description}>
                    <p>please report an error code if you have</p>
                </div>

                <form onSubmit={onSubmit}>
                    <input type="text" name="name" placeholder="hoge" />
                    <input
                        type="password"
                        name="password"
                        placeholder="password"
                    />
                    <input type="text" name="errorCode" placeholder="E000001" />
                    <button type="submit">Register</button>
                </form>
            </main>
        </>
    );
}
