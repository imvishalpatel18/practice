#stage 1
FROM node:4.12 as node
#npm uninstall node-sass
#npm i node-sass@4.14
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build --prod
#stage 2
FROM nginx:alpine
COPY --from=node /app/dist/client /usr/share/nginx/html