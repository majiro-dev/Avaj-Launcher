
SCENARIO:=scenario.txt

all:
	find * -name "*.java" > sources.txt
	javac @sources.txt

clean:
	rm -rf output/*

fclean: clean
	rm -rf avaj/*.class
	rm -rf avaj
	rm -rf sources.txt

run:
	java avaj.sources.Launcher scenarios/$(SCENARIO)

re: fclean all run