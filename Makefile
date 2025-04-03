
SCENARIO:=scenario.txt
CLASS_DIR:=class

all:
	find * -name "*.java" > sources.txt
	mkdir -p $(CLASS_DIR)
	javac -d $(CLASS_DIR) @sources.txt

clean:
	rm -rf output/

fclean: clean
	rm -rf $(CLASS_DIR)
	rm -rf sources.txt
	rm -rf */*.class

run:
	java -cp $(CLASS_DIR) sources.Launcher scenarios/$(SCENARIO)

re: fclean all run

runall:
	for scenario in scenarios/*.txt; do \
		echo "Running $$scenario"; \
		java -cp $(CLASS_DIR) sources.Launcher $$scenario; \
		mv output/simulation.txt output/`basename $$scenario`; \
	done

reall: fclean all runall

.PHONY: all clean fclean run re runall