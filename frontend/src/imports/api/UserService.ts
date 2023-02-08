import IUser from './models/IUser';
import RequestHandler from './RequestHandler';

class UserService extends RequestHandler {
    constructor() {
        super('http://localhost:8080');
    }

    async register(username: string, password: string): Promise<string> {
        const reqToken = await this.request('/register', {
            method: 'POST',
            body: {
                username: username,
                password: password,
            },
        });

        if (reqToken.status === 200) {
            const resToken = await reqToken.json();

            return resToken.token;
        }

        throw new Error();
    }

    async getByUsername(username: string): Promise<IUser> {
        const reqUser = await this.request('/user/username/' + username, {
            method: 'GET',
        });

        if (reqUser.status === 200) {
            const resUser = await reqUser.json();

            return {
                id: resUser.id,
                username: resUser.username,
                password: resUser.password,
                token: null,
            };
        }

        throw new Error();
    }

    async login(username: string, password: string): Promise<string> {
        const reqToken = await this.request('/login', {
            method: 'POST',
            body: {
                username: username,
                password: password,
            },
        });

        if (reqToken.status === 200) {
            const resToken = await reqToken.json();

            return resToken.token;
        }

        throw new Error();
    }
}

export default new UserService();
