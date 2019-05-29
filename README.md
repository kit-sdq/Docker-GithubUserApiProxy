# Docker-GithubUserApiProxy

[![](https://img.shields.io/docker/cloud/build/kitsdq/github-user-api-proxy.svg)](https://hub.docker.com/r/kitsdq/github-user-api-proxy/builds)
[![](https://img.shields.io/github/tag/kit-sdq/Docker-GithubUserApiProxy.svg)](https://hub.docker.com/r/kitsdq/github-user-api-proxy/tags)
[![](https://img.shields.io/github/issues/kit-sdq/Docker-GithubUserApiProxy.svg)](https://github.com/kit-sdq/Docker-GithubUserApiProxy/issues)
[![](https://img.shields.io/github/license/kit-sdq/Docker-GithubUserApiProxy.svg)](https://github.com/kit-sdq/Docker-GithubUserApiProxy/blob/master/LICENSE)

Docker image for a small Github user API proxy that works around the restriction that the user API endpoint only delivers email addresses if the user has set it to public. The container is meant to be used by OAuth applications that require email addresses but do not provide means to specify a separate API endpoint for this information.

Docker images are available on [DockerHub](https://hub.docker.com/r/kitsdq/github-user-api-proxy).