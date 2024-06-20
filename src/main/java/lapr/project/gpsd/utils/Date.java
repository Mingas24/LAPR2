package lapr.project.gpsd.utils;

import java.util.Calendar;

/**
 * Represents a date by day, month, year.
 *
 * @author ISEP-DEI-PPROG
 */
public class Date implements Comparable<Date>{

    /**
     * The year of the date.
     */
    private int year;
    
    /**
     * The month of the date.
     */
    private Month month;
    
    /**
     * The day of the date.
     */
    private int day;
    
    /**
     * The default year.
     */
    private static final int YEAR_DEFAULT = 1;
    
    /**
     * The default month.
     */
    private static final Month MONTH_DEFAULT = Month.JANUARY;
    
    /**
     * The default day.
     */
    private static final int DAY_DEFAULT = 1;
    
    /**
     * Represents the day of the week.
     */
    private static enum DayOfTheWeek{

        /**
         * Days of the week.
         */ 
        DOMINGO{ @Override public String toString() { return "Sunday"; } },
        SEGUNDA{ @Override public String toString() { return "Monday"; } },
        TERCA{   @Override public String toString() { return "Tuesday"; } },
        QUARTA{  @Override public String toString() { return "Wednesday"; } },
        QUINTA{  @Override public String toString() { return "Thursday"; } },
        SEXTA{   @Override public String toString() { return "Friday"; } },
        SABADO{  @Override public String toString() { return "Saturday"; } };

        /**
         * Returns the designation of the day of the week which order is given.
         * 
         * @param dayOfTheWeekOrder - Between 0-6. 0 being Sunday. 
         * @return - The designation of the day of the week.
         */
        public static String dayOfTheWeekDesignation(int dayOfTheWeekOrder) {
            return DayOfTheWeek.values()[dayOfTheWeekOrder].toString();
        }
    }

    /**
     * Represents the months of the year.
     */
    private static enum Month{

        /**
         * The months of the year.
         */
        JANUARY(31){  @Override public String toString() { return "January"; } }, 
        FEBRUARY(28){ @Override public String toString() { return "February"; } }, 
        MARCH(31){    @Override public String toString() { return "March"; } },
        APRIL(30){    @Override public String toString() { return "April"; } }, 
        MAY(31){      @Override public String toString() { return "May"; } }, 
        JUNE(30){     @Override public String toString() { return "June"; } }, 
        JULY(31){     @Override public String toString() { return "July"; } }, 
        AUGUST(31){   @Override public String toString() { return "August"; } }, 
        SEPTEMBER(30){@Override public String toString() { return "September"; } },
        OCTOBER(31){  @Override public String toString() { return "October"; } }, 
        NOVEMBER(30){ @Override public String toString() { return "November"; } }, 
        DECEMBER(31){ @Override public String toString() { return "December"; } };

        /**
         * The number of days on a month.
         */
        private int numberOfDays;

        /**
        * Constructs a month with the number of days given.
        * 
        * @param numberOfDays - Number of days on the month.
        */
        private Month(int numberOfDays) {
            this.numberOfDays = numberOfDays;
        }

        /**
         * Returns the number of days of the month of the given year.Devolve o número de dias do mês do year recebido por parâmetro.
         * 
         * @param year - The year of the month.
         * @return
         */
        public int numberOfDays(int year) {
            if (ordinal() == 1 && Date.isLeapYear(year)) {
                return numberOfDays + 1;
            }
            return numberOfDays;
        }
        
        /**
         * Devolve o mês cuja ordem é recebida por parâmetro.
         * 
         * @param ordemDoMes a ordem do mês.
         * @return o mês cuja ordem é recebida por parâmetro.
         */
        public static Month obterMes(int ordemDoMes) {
            return Month.values()[ordemDoMes - 1];
        }

    }
    
    /**
     * Constrói uma instância de Data recebendo o year, o mês e o day.
     *
     * @param ano o year da data.
     * @param mes o mês da data.
     * @param dia o day da data.
     */
    public Date(int ano, int mes, int dia) {
        setData(ano,mes,dia);
    }

    /**
     * Constrói uma instância de Data com a data por omissão.
     */
    public Date() {
        year = YEAR_DEFAULT;
        month = MONTH_DEFAULT;
        day = DAY_DEFAULT;
    }

    /**
     * Constrói uma instância de Data com as mesmas caraterísticas da data
     * recebida por parâmetro.
     *
     * @param outraData a data com as características a copiar.
     */
    public Date(Date outraData) {
        year = outraData.year;
        month = outraData.month;
        day = outraData.day;
    }

    /**
     * Devolve o year da data.
     *
     * @return year da data
     */
    public int getAno() {
        return year;
    }

    /**
     * Devolve o mês da data.
     *
     * @return mês da data.
     */
    public int getMes() {
        return month.ordinal()+1;
    }

    /**
     * Devolve o day da data.
     *
     * @return day da data.
     */
    public int getDia() {
        return day;
    }

    /**
     * Modifica o year, o mês e o day da data.
     *
     * @param ano o novo year da data.
     * @param mes o novo mês da data.
     * @param dia o novo day da data.
     */
    public final void setData(int ano, int mes, int dia) {
        if (mes < 1 || mes > 12) {
            throw new MesInvalidoException("Mês " + mes + " é inválido!!");
        }
        if (dia < 1 || dia > Month.obterMes(mes).numberOfDays(ano))  {
            throw new DiaInvalidoException("Dia " + ano + "/" + mes + "/" + dia
                    + " é inválido!!");
        }
        this.year = ano;         
        this.month = Month.obterMes(mes);       
        this.day = dia;
    }

    /**
     * Devolve a descrição textual da data no formato: diaDaSemana, day de mês
 de year.
     *
     * @return caraterísticas da data.
     */
    @Override
    public String toString() {
        return String.format("%s, %d de %s de %d", diaDaSemana(), day, month, year);
    }

    /**
     * Devolve a data no formato:%04d/%02d/%02d.
     *
     * @return caraterísticas da data.
     */
    public String toAnoMesDiaString() {
        return String.format("%04d/%02d/%02d", year, month.ordinal()+1, day);
    }
    
    /**
     * Compara a data com o objeto recebido.
     *
     * @param outroObjeto o objeto a comparar com a data.
     * @return true se o objeto recebido representar uma data equivalente à
     *         data. Caso contrário, retorna false.
     */
    @Override
    public boolean equals(Object outroObjeto) {
        if (this == outroObjeto) {
            return true;
        }
        if (outroObjeto == null || getClass() != outroObjeto.getClass()) {
            return false;
        }
        Date outraData = (Date) outroObjeto;
        return year == outraData.year && month.equals(outraData.month)
                && day == outraData.day;
    }

    /**
     * Compara a data com a outra data recebida por parâmetro.
     *
     * @param outraData a data a ser comparada.
     * @return o valor 0 se a outraData recebida é igual à data; o valor -1 se
     *         a outraData for posterior à data; o valor 1 se a outraData for 
     *         anterior à data.
     */
    @Override
    public int compareTo(Date outraData) {
        return (outraData.isLater(this)) ? -1 : (isLater(outraData)) ? 1 : 0;
    }

    /**
     * Devolve o day da semana da data.
     *
     * @return day da semana da data.
     */
    public String diaDaSemana() {
        int totalDias = contaDias();
        totalDias = totalDias % 7;

        return DayOfTheWeek.dayOfTheWeekDesignation(totalDias);
    }


    /**
     * Devolve true se a data for maior do que a data recebida por parâmetro. Se
     * a data for menor ou igual à data recebida por parâmetro, devolve false.
     *
     * @param outraData a outra data com a qual se compara a data.
     * @return true se a data for maior do que a data recebida por parâmetro,
     *         caso contrário devolve false.
     */
    public boolean isLater(Date outraData) {
        int totalDias = contaDias();
        int totalDias1 = outraData.contaDias();

        return totalDias > totalDias1;
    }

    public boolean isSooner(Date outraData) {
        int totalDias = contaDias();
        int totalDias1 = outraData.contaDias();

        return totalDias < totalDias1;
    }

    /**
     * Devolve a diferença em número de dias entre a data e a data recebida por
     * parâmetro.
     *
     * @param outraData a outra data com a qual se compara a data para calcular
     *                  a diferença do número de dias.
     * @return diferença em número de dias entre a data e a data recebida por
     *         parâmetro.
     */
    public int diference(Date outraData) {
        int totalDias = contaDias();
        int totalDias1 = outraData.contaDias();

        return Math.abs(totalDias - totalDias1);
    }

    /**
     * Devolve a diferença em número de dias entre a data e a data recebida por
 parâmetro com year, mês e day.
     *
     * @param ano o year da data com a qual se compara a data para calcular a
            diferença do número de dias.
     * @param mes o mês da data com a qual se compara a data para calcular a
     *            diferença do número de dias.
     * @param dia o day da data com a qual se compara a data para calcular a
            diferença do número de dias.
     * @return diferença em número de dias entre a data e a data recebida por
         parâmetro com year, mês e day.
     */
    public int diferenca(int ano, int mes, int dia) {
        int totalDias = contaDias();
        Date outraData = new Date(ano, mes, dia);
        int totalDias1 = outraData.contaDias();

        return Math.abs(totalDias - totalDias1);
    }

    /**
     * Devolve true se o year passado por parâmetro for bissexto. Se o year
 passado por parâmetro não for bissexto, devolve false.
     *
     * @param year o year a validar.
     * @return true se o year passado por parâmetro for bissexto, caso contrário
         devolve false.
     */
    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    /**
     * Devolve a data atual do sistema.
     *
     * @return a data atual do sistema.
     */
    public static Date dataAtual() {
        Calendar hoje = Calendar.getInstance();
        int ano = hoje.get(Calendar.YEAR);
        int mes = hoje.get(Calendar.MONTH) + 1;    // janeiro é representado por 0.
        int dia = hoje.get(Calendar.DAY_OF_MONTH);
        return new Date(ano, mes, dia);
    }

    /**
     * Devolve o número de dias desde o day 1/1/1 até à data.
     *
     * @return número de dias desde o day 1/1/1 até à data.
     */
    private int contaDias() {
        int totalDias = 0;

        for (int i = 1; i < year; i++) {
            totalDias += isLeapYear(i) ? 366 : 365;
        }
        for (int i = 1; i < month.ordinal()+1; i++) {
            totalDias += Month.obterMes(i).numberOfDays(year);
        }
        totalDias += day;

        return totalDias;
    }
    
    public boolean isBetween(Date firstDate, Date secondDate){
        return (firstDate.isSooner(this) && secondDate.isLater(this));
    }
}
