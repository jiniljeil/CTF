class API {
    constructor() {
        this.host = `http://${window.location.host}`;
        this.requestOptions = {
            method: "GET",
            cache: "no-cache",
            headers: {
                "Content-Type": "application/json",
                "accept": "application/json",
            },
        };
    }

    async communicate(method = "GET", endpoint = "/api/health", headers = {}, data = null, file = null) {
        this.requestOptions.method = method;

        if (file === null && data !== null) {
            this.requestOptions['body'] = JSON.stringify(data)
        }

        if (headers) {
            this.requestOptions['headers'] = {
                ...this.requestOptions['headers'],
                ...headers,
            }
        }

        if (file !== null) {
            this.requestOptions['headers'] = {
                "accept": "application/json",
                ...headers,
            }

            this.requestOptions['body'] = data;
        }

        try {
            const communication = await fetch(`${this.host}${endpoint}`, this.requestOptions)

            const result = await communication;

            if (result.headers.get("Content-Type").includes('application/json')) {
                const resp = await result.json();
                const status = result.status;

                return {
                    resp: resp,
                    status: status
                };
            }
            if (result.headers.get('Content-Type').includes('text/html')) {
                const resp = await result.text();
                const status = result.status;

                return {
                    resp: resp,
                    status: status
                };
            }

            const resp = await result.blob();
            const status = result.status;

            return {
                resp: resp,
                status: status
            };

        } catch (e) {
            throw new Error('API is not working...');
        }
    }
}

export {API};
