import ITeammate from './models/ITeammate';
import RequestHandler from './RequestHandler';

class TeammateService extends RequestHandler {
    constructor() {
        super('http://localhost:8086');
    }

    async getAllByCharacterId(characterId: string): Promise<ITeammate[]> {
        let teammates: ITeammate[] = [];

        const reqTeammates = await this.request('/team/' + characterId, {
            method: 'GET',
        });

        if (reqTeammates.status === 200) {
            const resTeammates = await reqTeammates.json();

            for (const teammate of resTeammates) {
                teammates.push(teammate);
            }

            return teammates;
        }

        throw new Error();
    }

    async swap(
        characterId: string,
        teammateOneId: string,
        teammateTwoId: string
    ): Promise<ITeammate[]> {
        const reqSwap = await this.request('/team/' + characterId + '/swap', {
            method: 'PUT',
            body: [
                {
                    id: teammateOneId,
                },
                {
                    id: teammateTwoId,
                },
            ],
        });

        if (reqSwap.status === 200) {
            let teammates: ITeammate[] = [];

            const resSwap = await reqSwap.json();

            for (const teammate of resSwap) {
                teammates.push(teammate);
            }

            return teammates;
        }

        throw new Error();
    }

    async delete(characterId: string, teammate: ITeammate): Promise<ITeammate[]> {
        const reqDel = await this.request('/team/' + characterId, {
            method: 'DELETE',
            body: {
                id: teammate.id,
            },
        });

        if (reqDel.status === 200) {
            let teammates: ITeammate[] = [];

            const resDel = await reqDel.json();

            for (const teammate of resDel) {
                teammates.push(teammate);
            }

            return teammates;
        }

        throw new Error();
    }
}

export default new TeammateService();
