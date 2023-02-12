class RequestHandler {
    host: string;

    constructor(host: string) {
        this.host = host;
    }

    async request(uri: string, options: { method: string; body?: object }) {
        const { method, body } = options;
        let headers = {
            'Content-Type': 'application/json',
        };

        return fetch(this.host + uri, {
            method: method,
            headers: headers,
            body: JSON.stringify(body),
        });
    }
}

export default RequestHandler;
