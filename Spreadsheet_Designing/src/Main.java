class Spreadsheet {
    private int rows;
    private int cols;
    private int[][] grid;

    public Spreadsheet(int rows) {
        this.rows = rows;
        this.cols = 26;
        this.grid = new int[rows][this.cols];
        // Initialize grid with zeros
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                grid[i][j] = 0;
            }
        }
    }

    public void setCell(String cell, int value) {
        char colChar = cell.charAt(0);
        String rowStr = cell.substring(1);
        int rowIndex = Integer.parseInt(rowStr) - 1;
        int colIndex = colChar - 'A';
        grid[rowIndex][colIndex] = value;
    }

    public void resetCell(String cell) {
        char colChar = cell.charAt(0);
        String rowStr = cell.substring(1);
        int rowIndex = Integer.parseInt(rowStr) - 1;
        int colIndex = colChar - 'A';
        grid[rowIndex][colIndex] = 0;
    }

    public int getValue(String formula) {
        if (!formula.startsWith("=")) {
            return evalToken(formula);
        }
        String expr = formula.substring(1);
        int idx = expr.indexOf('+');
        if (idx == -1) {
            return evalToken(expr);
        }
        String left = expr.substring(0, idx);
        String right = expr.substring(idx + 1);
        return evalToken(left) + evalToken(right);
    }

    private int evalToken(String token) {
        // Check if token is a number
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            // Token is a cell reference
            char colChar = token.charAt(0);
            String rowStr = token.substring(1);
            int rowIndex = Integer.parseInt(rowStr) - 1;
            int colIndex = colChar - 'A';
            return grid[rowIndex][colIndex];
        }
    }

    public static void main(String[] args) {
        // Create a new spreadsheet with 5 rows
        Spreadsheet spreadsheet = new Spreadsheet(5);

        // Set values in various cells
        spreadsheet.setCell("A1", 5);  // Set A1 to 5
        spreadsheet.setCell("B1", 10); // Set B1 to 10
        spreadsheet.setCell("C1", 3);  // Set C1 to 3

        // Retrieve a single value from the grid
        System.out.println("Value of A1: " + spreadsheet.getValue("A1"));  // Expected output: 5
        System.out.println("Value of B1: " + spreadsheet.getValue("B1"));  // Expected output: 10

        // Use a formula to sum A1 and B1
        System.out.println("Formula A1 + B1: " + spreadsheet.getValue("=A1+B1"));  // Expected output: 15

        // Reset the value of A1
        spreadsheet.resetCell("A1");

        // Verify if A1 is reset
        System.out.println("Value of A1 after reset: " + spreadsheet.getValue("A1"));  // Expected output: 0

        // Set values again after reset
        spreadsheet.setCell("A1", 8);
        spreadsheet.setCell("B1", 7);

        // Use a formula again to sum A1 and B1
        System.out.println("Formula A1 + B1 after reset: " + spreadsheet.getValue("=A1+B1"));  // Expected output: 15

    }
}