FROM oven/bun:1.0.35-slim
RUN apt-get update && apt-get install -y curl

ARG FLAG
ARG LOGFILE

RUN useradd -u 1001 ctf
RUN echo $FLAG > /flag && chown root:ctf /flag && chmod a-wx /flag

WORKDIR /home/ctf

COPY ./src/package.json ./src/bun.lockb ./src/jsconfig.json ./
RUN bun install --production --frozen-lockfile

COPY ./src/index.js .
RUN touch $LOGFILE && chown root:ctf $LOGFILE && chmod 660 $LOGFILE

USER ctf
ENV NODE_ENV=production
ENV LOGFILE=$LOGFILE
CMD ["bun", "index.js"]