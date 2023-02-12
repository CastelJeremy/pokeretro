FROM node:lts-alpine3.17
RUN npm install webpack -g
WORKDIR /app/front
COPY ./ ./
RUN npm install
EXPOSE 9000
CMD ["npm", "run", "serve-prod"]