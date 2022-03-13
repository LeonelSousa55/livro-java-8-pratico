/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package captulo10.java.time;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Locale;

/**
 *
 * @author Leonel
 */
public class Captulo10 {

    public Captulo10() {

        System.out.println("\n10.2-Resgatando a data do mês que vem, forma antiga");
        Calendar mesQueVem = Calendar.getInstance();
        mesQueVem.add(Calendar.MONTH, 1);
        System.out.println("mesQueVem:" + mesQueVem.getTime());

        System.out.println("\n10.2-Resgatando a data do mês que vem, nova forma");
        LocalDate mesQueVem1 = LocalDate.now().plusMonths(1);
        System.out.println("mesQueVem1:" + mesQueVem1);

        System.out.println("\n10.2-Reduzir um ano da data atual");
        LocalDate anoPassado = LocalDate.now().minusYears(1);
        System.out.println("anoPassado:" + anoPassado);

        System.out.println("\n10.2-Pegar também o horário");
        LocalDateTime agora = LocalDateTime.now();
        System.out.println("agora:" + agora);

        System.out.println("\n10.2-Resgatando apenas a hora");
        LocalTime hora = LocalTime.now();
        System.out.println("hora:" + hora);

        System.out.println("\n10.2-Criar um LocalDateTime com uma hora específica");
        LocalDateTime hojeMeioDia = LocalDate.now().atTime(12, 0);
        System.out.println("hojeMeioDia:" + hojeMeioDia);

        System.out.println("\n10.2-Unindo a data com a hora");
        LocalTime agora1 = LocalTime.now();
        LocalDate hoje1 = LocalDate.now();
        LocalDateTime dataEHora = hoje1.atTime(agora1);
        System.out.println("dataEHora:" + dataEHora);

        System.out.println("\n10.2-Construir ZonedDateTime a partir da LocalDateTime");
        ZonedDateTime dataComHoraETimeZone = dataEHora.atZone(ZoneId.of("America/Sao_Paulo"));
        System.out.println("dataComHoraETimeZone:" + dataComHoraETimeZone);

        System.out.println("\n10.2-Converter para outra medida de tempo");
        LocalDateTime semTimeZone = dataComHoraETimeZone.toLocalDateTime();
        System.out.println("semTimeZone:" + semTimeZone);

        System.out.println("\n10.2-Construindo instâncias de datas específicas");
        LocalDate date = LocalDate.of(2014, 12, 05);
        LocalDateTime dateTime = LocalDateTime.of(2014, 12, 05, 10, 25);
        System.out.println("date:" + date);
        System.out.println("dateTime:" + dateTime);

        System.out.println("\n10.2-Construindo a data atual com um ano específico");
        LocalDate dataDoPassado = LocalDate.now().withYear(1988);
        System.out.println("dataDoPassado:" + dataDoPassado);

        System.out.println("\n10.2-Resgatando o ano de uma data");
        System.out.println("Ano da data:" + dataDoPassado.getYear());

        System.out.println("\n10.2-Comparando as datas");
        LocalDate hoje = LocalDate.now();
        LocalDate amanha = LocalDate.now().plusDays(1);
        System.out.println("isBefore:" + hoje.isBefore(amanha));
        System.out.println("isAfter:" + hoje.isAfter(amanha));
        System.out.println("isEqual:" + hoje.isEqual(amanha));

        System.out.println("\n10.2-Comparando as datas, com times zones diferentes");
        ZonedDateTime tokyo = ZonedDateTime.of(2011, 5, 2, 10, 30, 0, 0, ZoneId.of("Asia/Tokyo"));
        ZonedDateTime saoPaulo = ZonedDateTime.of(2011, 5, 2, 10, 30, 0, 0, ZoneId.of("America/Sao_Paulo"));
        System.out.println("isEqual:" + tokyo.isEqual(saoPaulo));

        tokyo = tokyo.plusHours(12);
        System.out.println("isEqual:" + tokyo.isEqual(saoPaulo));

        System.out.println("\n10.2-Resgatar o dia do mês atual");
        System.out.println("Hoje é dia:" + MonthDay.now().getDayOfMonth());

        System.out.println("\n10.2-Resgatar o mês e o ano de uma determinada data");
        YearMonth ym = YearMonth.from(date);
        System.out.println(ym.getMonth() + " " + ym.getYear());

        System.out.println("\n10.3-Enuns no lugar de constantes");
        System.out.println(LocalDate.of(2014, 12, 25));
        System.out.println(LocalDate.of(2014, Month.DECEMBER, 25));

        System.out.println("\n10.3-Primeiro dia do trimestre");
        System.out.println(Month.DECEMBER.firstMonthOfQuarter());
        System.out.println(Month.DECEMBER.plus(2));
        System.out.println(Month.DECEMBER.minus(1));

        System.out.println("\n10.3-Formatando a saída com Locale");
        Locale pt = new Locale("pt");
        System.out.println(Month.DECEMBER.getDisplayName(TextStyle.FULL, pt));
        System.out.println(Month.DECEMBER.getDisplayName(TextStyle.SHORT, pt));

        System.out.println("\n10.4-Formatando as datas");
        LocalDateTime agora2 = LocalDateTime.now();
        String resultado = agora2.format(DateTimeFormatter.ISO_LOCAL_TIME);
        System.out.println(resultado);

        System.out.println("\n10.4-Formatando as datas");
        LocalDateTime agora3 = LocalDateTime.now();
        String resultado1 = agora3.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println(resultado1);

        System.out.println("\n10.4-Convertendo uma string em um localDate");
        LocalDateTime agora4 = LocalDateTime.now();
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String resultado2 = agora4.format(formatador);
        LocalDate agoraEmData = LocalDate.parse(resultado2, formatador);
        System.out.println(agoraEmData);

        System.out.println("\n10.5-Datas inválidas, com Calendar");
        Calendar instante = Calendar.getInstance();
        instante.set(2014, Calendar.FEBRUARY, 30);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        System.out.println(dateFormat.format(instante.getTime()));

        System.out.println("\n10.5-Datas inválidas, com Local date retorna um erro");
        //LocalDate.of(2014, Month.FEBRUARY, 30);

        System.out.println("\n10.5-Outro erro com DateTime");
        //LocalDateTime horaInvalida = LocalDate.now().atTime(25, 0);

        System.out.println("\n10.6-Diferença em dias entre duas datas, Calendar");
        Calendar calendar = Calendar.getInstance();
        Calendar outraData = Calendar.getInstance();
        outraData.set(2002, Calendar.MARCH, 7);
        long diferenca = calendar.getTimeInMillis() - outraData.getTimeInMillis();
        long milissegundosDeUmDia = 1000 * 60 * 60 * 24;
        long dias = diferenca / milissegundosDeUmDia;
        System.out.println("dias:" + dias);

        System.out.println("\n10.6-Diferença em dias entre duas datas, usnado ChrnoUnit");
        LocalDate agora5 = LocalDate.now();
        LocalDate outraData5 = LocalDate.of(2002, Month.MARCH, 7);
        Long dias5 = ChronoUnit.DAYS.between(outraData5, agora5);
        System.out.println("dias5:" + dias5);

        System.out.println("\n10.6-Diferença completa entre duas datas, usnado ChrnoUnit");
        Long dias6 = ChronoUnit.DAYS.between(outraData5, agora5);
        Long meses6 = ChronoUnit.MONTHS.between(outraData5, agora5);
        Long anos6 = ChronoUnit.YEARS.between(outraData5, agora5);
        System.out.printf("%s dias, %s meses e %s anos", dias6, meses6, anos6);
        System.out.println("");

        System.out.println("\n10.6-Diferença completa entre duas datas, usando Period");
        LocalDate agora6 = LocalDate.now();
        LocalDate outraData6 = LocalDate.of(2022, Month.FEBRUARY, 10);
        Period periodo6 = Period.between(outraData6, agora6);
        System.out.printf("%s dias, %s meses e %s anos", periodo6.getDays(), periodo6.getMonths(), periodo6.getYears());
        System.out.println("");

        System.out.println("\n10.6-Diferença completa entre duas datas, usnado Period, ano maior");
        LocalDate agora7 = LocalDate.now();
        LocalDate outraData7 = LocalDate.of(2025, Month.FEBRUARY, 10);
        Period periodo7 = Period.between(outraData7, agora7);
        System.out.printf("%s dias, %s meses e %s anos", periodo7.getDays(), periodo7.getMonths(), periodo7.getYears());
        System.out.println("");

        System.out.println("\n10.6-Caso a diferença entre as datas sojam negativas podemos inverter o periodo");
        if (periodo7.isNegative()) {
            periodo7 = periodo7.negated();
        }
        System.out.printf("%s dias, %s meses e %s anos", periodo7.getDays(), periodo7.getMonths(), periodo7.getYears());
        System.out.println("");

        System.out.println("\n10.6-Criando um Period");
        Period periodo2 = Period.of(2, 10, 5);

        System.out.println("\n10.6-Trabalhando com duração");
        LocalDateTime agora8 = LocalDateTime.now();
        LocalDateTime daquiAUmaHora8 = agora8.plusHours(2);
        Duration duration = Duration.between(agora8, daquiAUmaHora8);

        if (duration.isNegative()) {
            duration = duration.negated();
        }
        System.out.printf("%s horas, %s minutos e %s segundos", duration.toHours(), duration.toMinutes(), duration.getSeconds());
    }

    public static void main(String[] args) {
        new Captulo10();
    }
}
