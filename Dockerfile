FROM alpine:3.10.1 AS base

RUN    apk --no-cache add openjdk11=11.0.4_p4-r1 \
    && rm -rf /var/cache/apk/* \
    && /usr/lib/jvm/java-11-openjdk/bin/jlink \
       --module-path /usr/lib/jvm/java-11-openjdk/jmods \
       --compress=2 \
       --add-modules java.base,java.sql \
       --no-header-files \
       --no-man-pages \
       --output /opt/openjdk-11-minimal

FROM alpine:3.10.1

ARG fritter_version="1.0.0"

LABEL maintainer="Haruaki Tamada" \
      version="${fritter_version}" \
      description="Checking tool for object oriented exercises by nine rules. "

COPY --from=base /opt/openjdk-11-minimal /opt/openjdk-11-minimal

RUN    adduser -D fritter \
    && apk --no-cache add --virtual .builddeps curl unzip  \
    && curl -s -L -O "https://github.com/tamada/fritter/releases/download/v${fritter_version}/fritter-${fritter_version}-dist.zip" \
#    && curl -s -L -o fritter-${fritter_version}-dist.zip https://www.dropbox.com/s/l5e94mg4bpvumk2/fritter-${fritter_version}-dist.zip?dl=0 \
    && unzip -q "fritter-${fritter_version}-dist.zip"       \
    && mv "fritter-${fritter_version}" /opt                 \
    && ln -s "/opt/fritter-${fritter_version}" /opt/fritter \
    && rm "fritter-${fritter_version}-dist.zip"             \
    && chmod 755 /opt/fritter/bin/fritter                   \
    && apk del --purge .builddeps

ENV JAVA_HOME="/opt/openjdk-11-minimal"
ENV PATH="$PATH:$JAVA_HOME/bin:/opt/fritter/bin"
ENV HOME="/home/fritter"
ENV FRITTER_HOME="/opt/fritter"

WORKDIR /home/fritter
USER    fritter

ENTRYPOINT [ "fritter" ]
