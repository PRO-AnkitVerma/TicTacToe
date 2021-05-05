package tictactoe.utils;


public enum Symbol {
    CIRCLE {
        public String toString() {
            return "O";
        }
    },

    CROSS {
        public String toString() {
            return "X";
        }
    },

    EMPTY {
        public String toString() {
            return " ";
        }
    }
}
