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
        let teammates: ITeammate[] = [];

        const reqTeammates = await this.request(
            '/team/' + characterId + '/swap',
            {
                method: 'PUT',
                body: [
                    {
                        id: teammateOneId,
                    },
                    {
                        id: teammateTwoId,
                    },
                ],
            }
        );

        if (reqTeammates.status === 200) {
            const resTeammates = await reqTeammates.json();

            for (const teammate of resTeammates) {
                teammates.push(teammate);
            }

            return teammates;
        }

        throw new Error();
    }
}

export default new TeammateService();
