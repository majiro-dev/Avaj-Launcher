
SCENARIO:=scenario.txt

all:
	javac -d . sources/*.java

clean:
	rm -rf output/*

fclean: clean
	rm -rf avaj/*.class
	rm -rf avaj

run:
	java avaj.sources.Launcher scenarios/$(SCENARIO)

re: fclean all run