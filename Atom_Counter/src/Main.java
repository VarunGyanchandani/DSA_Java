import java.util.*;

public class Main {
    public String countOfAtoms(String formula) {
        Map<String, Integer> atomCount = parseFormula(formula);
        List<String> sortedAtoms = new ArrayList<>(atomCount.keySet());
        Collections.sort(sortedAtoms);

        StringBuilder result = new StringBuilder();
        for (String atom : sortedAtoms) {
            result.append(atom);
            int count = atomCount.get(atom);
            if (count > 1) {
                result.append(count);
            }
        }

        return result.toString();
    }

    private Map<String, Integer> parseFormula(String formula) {
        Stack<Map<String, Integer>> stack = new Stack<>();
        Map<String, Integer> currentCount = new HashMap<>();
        int i = 0;
        int n = formula.length();

        while (i < n) {
            if (formula.charAt(i) == '(') {
                stack.push(currentCount);
                currentCount = new HashMap<>();
                i++;
            } else if (formula.charAt(i) == ')') {
                i++;
                int multiplier = 0;
                while (i < n && Character.isDigit(formula.charAt(i))) {
                    multiplier = multiplier * 10 + (formula.charAt(i) - '0');
                    i++;
                }
                multiplier = (multiplier == 0) ? 1 : multiplier; // Default multiplier is 1

                // Combine currentCount with the previous count in the stack
                Map<String, Integer> previousCount = stack.pop();
                for (Map.Entry<String, Integer> entry : currentCount.entrySet()) {
                    previousCount.put(entry.getKey(), previousCount.getOrDefault(entry.getKey(), 0) + entry.getValue() * multiplier);
                }
                currentCount = previousCount;
            } else {
                // Parse element
                int start = i++;
                while (i < n && Character.isLowerCase(formula.charAt(i))) {
                    i++;
                }
                String element = formula.substring(start, i);

                // Parse count
                int count = 0;
                while (i < n && Character.isDigit(formula.charAt(i))) {
                    count = count * 10 + (formula.charAt(i) - '0');
                    i++;
                }
                count = (count == 0) ? 1 : count; // Default count is 1
                currentCount.put(element, currentCount.getOrDefault(element, 0) + count);
            }
        }

        return currentCount;
    }

    public static void main(String[] args) {
        Main counter = new Main();
        System.out.println(counter.countOfAtoms("H2O"));            // Output: "H2O"
        System.out.println(counter.countOfAtoms("Mg(OH)2"));        // Output: "H2MgO2"
        System.out.println(counter.countOfAtoms("K4(ON(SO3)2)2"));  // Output: "K4N2O14S4"
    }
}
