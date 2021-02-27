PWD := ${CURDIR}
DOCKER_IMAGE_NAME := wwwfritter
JOJOMI_VERSION := 0.69.0
DOCKER_IMAGE := jojomi/hugo:${JOJOMI_VERSION}
DEST := ${PWD}/../../target/site

define __docker_run
	docker run --rm --name $1 $2 --interactive --volume ${PWD}:/src --volume ${DEST}:/output --workdir /src --entrypoint hugo $(DOCKER_IMAGE) $3
endef

define __commit_and_push
	git -C $1 commit -a -m $2
	git -C $1 push origin gh-pages
endef

build: ${DEST}/index.html
	$(call __docker_run,${DOCKER_IMAGE_NAME}_build,,--buildFuture --destination /output)
	rm ${DEST}/favicon* ${DEST}/apple-touch-icon.png

${DEST}/index.html:
	git worktree prune
	git worktree add ${DEST} gh-pages

start:
	$(call __docker_run,${DOCKER_IMAGE_NAME},-d --publish 1313:1313,server --bind="0.0.0.0" --buildFuture)

stop:
	docker stop ${DOCKER_IMAGE_NAME}


deploy: pull build
	$(call __commit_and_push,../../target/site,"automatically update for publish the web pages")
