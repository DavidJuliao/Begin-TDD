public class CamelCaseConverter {
    public String converter(String nome) {
        return Character.toUpperCase(nome.charAt(0)) + nome.substring(1).toLowerCase();
    }
}
