
SCENARIO:=scenario.txt
CLASS_DIR:=class

all:
	find * -name "*.java" > sources.txt
	mkdir -p $(CLASS_DIR)
	javac -d $(CLASS_DIR) @sources.txt

clean:
	rm -rf output/*

fclean: clean
	rm -rf $(CLASS_DIR)
	rm -rf sources.txt

run:
	java -cp $(CLASS_DIR) avaj.sources.Launcher scenarios/$(SCENARIO)

re: fclean all run