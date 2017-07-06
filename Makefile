EXECUTABLES = java
EXEC_CHECK := $(foreach exec,$(EXECUTABLES), \
	$(if $(shell which $(exec)),some string,$(error "No $(exec) in PATH")))
OPTS=-XX:+TieredCompilation -XX:TieredStopAtLevel=1 -Xms256m -Xmx512m \
	-XX:ReservedCodeCacheSize=512m -XX:+UseConcMarkSweepGC -XX:+UseParNewGC
JAVA_OPTS=JAVA_TOOL_OPTIONS='-Xmx512m -XX:MaxPermSize=512m -Xms512m'
MVN=MAVEN_OPTS="$(OPTS)" $(JAVA_OPTS) mvn -T 1.5C
WEB_DIR=src/main/resources/web
REACT_BUILD_DIR=mirror-react/build

#################################################################
# Basic Tasks
#################################################################

default: clean build check

#################################################################
# Build Tasks
#################################################################

clean:
	$(MVN) clean
	rm -rf out logs build .gradle */logs $(WEB_DIR)/* $(REACT_BUILD_DIR)

test:
	$(MVN) test

check:
	$(MVN) verify -DskipTests -DskipAssembly

verify:
	$(MVN) verify

install:
	$(MVN) install

jar:
	$(MVN) package -DskipTests -DskipAssembly

build:
	$(MVN) package

rebuild: clean build

int-test:
	$(MVN) -Pintegration failsafe:integration-test failsafe:verify

build-react:
	cd mirror-react && npm run build
	mkdir -p $(WEB_DIR)
	cp -r $(REACT_BUILD_DIR)/* $(WEB_DIR)/
