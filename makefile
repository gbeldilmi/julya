#------------------------------------------------------------------------------#
# Project name & files                                                         #
#------------------------------------------------------------------------------#
PROJECT_NAME        := julya
JAR_FILE            := $(PROJECT_NAME).jar
MANIFEST_FILE       := manifest.mf
JAVA_FILES          := $(shell find $(PROJECT_NAME)/ -type f -name '*.java')
CLASS_FILES         := $(JAVA_FILES:%.java=%.class)
LIBRARY_DIR         := lib
CLASSPATH           := -classpath .#:$(LIBRARY_DIR)/<name>.jar
COMPILER_FLAGS      := -Xlint $(CLASSPATH)

#------------------------------------------------------------------------------#
# Commands                                                                     #
#------------------------------------------------------------------------------#
.PHONY : all, clean
all :
	javac $(COMPILER_FLAGS) $(JAVA_FILES)
	jar cfmv $(JAR_FILE) $(MANIFEST_FILE) $(CLASS_FILES) #$(LIBRARY_DIR)/
clean :
	rm -v $(JAR_FILE) $(CLASS_FILES)
