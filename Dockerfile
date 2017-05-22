FROM node:6.9.2

RUN mkdir -p /usr/src/app
RUN mkdir /usr/src/app/config

COPY ./lib /usr/src/app/lib
COPY ./node_modules /usr/src/app/node_modules
COPY ./package.json /usr/src/app/
COPY ./config/custom-environment-variables.coffee /usr/src/app/config/
COPY ./config/default.coffee /usr/src/app/config/
COPY ./doc /usr/src/app/doc

RUN mkdir -p /usr/src/app/files
RUN mkdir -p /usr/src/app/filesTmp


WORKDIR /usr/src/app
CMD node lib/index.js
