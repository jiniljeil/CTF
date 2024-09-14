import { GetServerSideProps } from "next";

export default function Error403() {
    return (
        <>
            <main>
                <h1>403 - You do not have permission</h1>
            </main>
        </>
    );
}

export const getServerSideProps: GetServerSideProps = async (ctx) => {
    ctx.res.statusCode = 403;
    return { props: {} };
}
