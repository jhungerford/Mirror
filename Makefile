EXECUTABLES = java
EXEC_CHECK := $(foreach exec,$(EXECUTABLES), \
	$(if $(shell which $(exec)),some string,$(error "No $(exec) in PATH")))
OPTS=-XX:+TieredCompilation -XX:TieredStopAtLevel=1 -Xms256m -Xmx512m \
	-XX:ReservedCodeCacheSize=512m -XX:+UseConcMarkSweepGC -XX:+UseParNewGC
JAVA_OPTS=JAVA_TOOL_OPTIONS='-Xmx512m -XX:MaxPermSize=512m -Xms512m'
MVN=MAVEN_OPTS="$(OPTS)" $(JAVA_OPTS) mvn -T 1.5C

#################################################################
# Basic Tasks
#################################################################

default: clean build check

#################################################################
# Build Tasks
#################################################################

clean:
	$(MVN) clean
	rm -rf out logs build .gradle */logs

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
