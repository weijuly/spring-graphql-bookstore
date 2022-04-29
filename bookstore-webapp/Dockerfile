FROM node:14

RUN npm config set strict-ssl false
WORKDIR /usr/src/app
COPY package*.json ./
RUN npm install
COPY . ./
EXPOSE 8000
CMD ["npm", "start"]