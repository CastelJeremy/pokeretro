import RequestHandler from './RequestHandler';

class MoneyService extends RequestHandler {
    constructor() {
        super('http://localhost:8082');
    }

    async get(characterId: string): Promise<number> {
        const reqMoney = await this.request('/money?idTrainer=' + characterId, {
            method: 'GET'
        });

        if (reqMoney.status === 200) {
            const resMoney = await reqMoney.json();

            return resMoney.amount;
        }

        throw new Error();
    }
}

export default new MoneyService();
