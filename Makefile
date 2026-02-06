JAVAC = javac
JAVA  = java
MAIN_CLASS = Main
BIN_DIR = bin
SRC_FILES = $(wildcard *.java)

.PHONY: all build run clean help

all: build

build:
	$(JAVAC) $(SRC_FILES)
	@echo "Сборка завершена"

run: build
	$(JAVA) $(MAIN_CLASS) $(ARGS)

run-short:
	$(MAKE) run ARGS="-s -a -p sample_ in1.txt in2.txt"

run-full:
	$(MAKE) run ARGS="-f -o ./output in1.txt in2.txt"

clean-linux:
	rm -f *.class
	@echo "Все .class файлы удалены (Unix)"

clean-win:
	del /Q *.class 2>nul || true
	@echo "Все .class файлы удалены (Windows)"

help:
	@echo "Доступные команды:"
	@echo "  make build         → скомпилировать проект"
	@echo "  make run           → запустить (нужно передать ARGS=... в двойных кавычках)"
	@echo "  make run-short     → пример с -s -a -p"
	@echo "  make run-full      → пример с -f -o"
	@echo "  make clean-linux   → удалить все .class (Linux/macOS/WSL)"
	@echo "  make clean-win     → удалить все .class (Windows cmd/PowerShell)"
	@echo "  make help          → эта справка"
