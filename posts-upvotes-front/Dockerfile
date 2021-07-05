FROM    node:12-slim as node
WORKDIR /app/
COPY    package.json .
RUN     npm i npm@latest -g
RUN     npm install
COPY    . .
RUN     npm run build

FROM    nginx:1.13
COPY    --from=node /app/dist/post-upvotes-front /usr/share/nginx/html
COPY    ./nginx-custom.conf /etc/nginx/conf.d/default.conf

EXPOSE  4200