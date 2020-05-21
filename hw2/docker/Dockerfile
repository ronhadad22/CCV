FROM alpine:3.11.5
RUN apk add iperf3
ARG PORT
ENV PORT_NUM=$PORT
EXPOSE ${PORT_NUM}


CMD iperf3 -s -p ${PORT_NUM}


