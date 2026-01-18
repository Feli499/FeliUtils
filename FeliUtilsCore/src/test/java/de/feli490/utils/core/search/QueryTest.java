package de.feli490.utils.core.search;

import de.feli490.utils.core.search.exceptions.ParseException;
import de.feli490.utils.core.search.parameters.ParameterValue;
import de.feli490.utils.core.search.parameters.StringParameterValue;
import de.feli490.utils.core.search.parameters.identifier.StringParameterIdentifier;
import de.feli490.utils.core.search.query.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class QueryTest {

    private Person herbert1;
    private Person markus;
    private Person herbert2;
    private List<Person> personList;
    private StringParameterIdentifier nameIdentifier;
    private StringParameterIdentifier nachnameIdentifier;
    private de.feli490.utils.core.search.ParameterIdentifierContainer parameterIdentifierContainer;

    @Before
    public void setup() {

        parameterIdentifierContainer = new de.feli490.utils.core.search.ParameterIdentifierContainer();

        nachnameIdentifier = new StringParameterIdentifier("nachname");
        nameIdentifier = new StringParameterIdentifier("name");

        parameterIdentifierContainer.registerIdentifier(nachnameIdentifier);
        parameterIdentifierContainer.registerIdentifier(nameIdentifier);

        personList = new ArrayList<>();

        StringParameterValue herbertNameMock = Mockito.mock(StringParameterValue.class);
        Mockito.when(herbertNameMock.getValue()).thenReturn("Herbert");
        Mockito.when(herbertNameMock.getParameterIdentifier()).thenReturn(nameIdentifier);

        StringParameterValue markusNameMock = Mockito.mock(StringParameterValue.class);
        Mockito.when(markusNameMock.getValue()).thenReturn("Markus");
        Mockito.when(markusNameMock.getParameterIdentifier()).thenReturn(nameIdentifier);

        StringParameterValue maierNachnameMock = Mockito.mock(StringParameterValue.class);
        Mockito.when(maierNachnameMock.getValue()).thenReturn("Maier");
        Mockito.when(maierNachnameMock.getParameterIdentifier()).thenReturn(nachnameIdentifier);

        StringParameterValue gruenNachnameMock = Mockito.mock(StringParameterValue.class);
        Mockito.when(gruenNachnameMock.getValue()).thenReturn("Grün");
        Mockito.when(gruenNachnameMock.getParameterIdentifier()).thenReturn(nachnameIdentifier);

        herbert1 = new Person(herbertNameMock, maierNachnameMock);
        markus = new Person(markusNameMock, maierNachnameMock);
        herbert2 = new Person(herbertNameMock, gruenNachnameMock);

        personList.add(herbert1);
        personList.add(markus);
        personList.add(herbert2);
    }

    @Test
    public void testNachname() throws ParseException {

        setup();

        de.feli490.utils.core.search.QueryParser parser = new de.feli490.utils.core.search.QueryParser(parameterIdentifierContainer);

        Query query = parser.parse("nachname = \"Grün\"");
        List<Person> filtered = query.filter(personList);

        Assert.assertFalse(filtered.contains(herbert1));
        Assert.assertTrue(filtered.contains(herbert2));
        Assert.assertFalse(filtered.contains(markus));

        query = parser.parse("nachname = \"Grasdaasdün\"");
        filtered = query.filter(personList);

        Assert.assertFalse(filtered.contains(herbert1));
        Assert.assertFalse(filtered.contains(herbert2));
        Assert.assertFalse(filtered.contains(markus));
    }

    @Test
    public void testName() throws ParseException {

        setup();

        de.feli490.utils.core.search.QueryParser parser = new de.feli490.utils.core.search.QueryParser(parameterIdentifierContainer);

        Query query = parser.parse("name = \"Herbert\"");
        List<Person> filtered = query.filter(personList);

        Assert.assertTrue(filtered.contains(herbert1));
        Assert.assertTrue(filtered.contains(herbert2));
        Assert.assertFalse(filtered.contains(markus));

        query = parser.parse("(name = \"Herbert\")");
        filtered = query.filter(personList);

        Assert.assertTrue(filtered.contains(herbert1));
        Assert.assertTrue(filtered.contains(herbert2));
        Assert.assertFalse(filtered.contains(markus));

        query = parser.parse("name = \"Herasdasdbert\"");
        filtered = query.filter(personList);

        Assert.assertFalse(filtered.contains(herbert1));
        Assert.assertFalse(filtered.contains(herbert2));
        Assert.assertFalse(filtered.contains(markus));
    }

    @Test
    public void testNameUNachnameEinzelneKlammern() throws ParseException {

        setup();

        de.feli490.utils.core.search.QueryParser parser = new de.feli490.utils.core.search.QueryParser(parameterIdentifierContainer);

        Query query = parser.parse("name = \"Herbert\" and (nachname = \"Maier\")");
        List<Person> filtered = query.filter(personList);

        Assert.assertTrue(filtered.contains(herbert1));
        Assert.assertFalse(filtered.contains(herbert2));
        Assert.assertFalse(filtered.contains(markus));

        query = parser.parse("(name = \"Herbert\") and nachname = \"Maier\"");
        filtered = query.filter(personList);

        Assert.assertTrue(filtered.contains(herbert1));
        Assert.assertFalse(filtered.contains(herbert2));
        Assert.assertFalse(filtered.contains(markus));
    }

    @Test
    public void testNameUNachnameSeperateKlammern() throws ParseException {

        setup();

        de.feli490.utils.core.search.QueryParser parser = new de.feli490.utils.core.search.QueryParser(parameterIdentifierContainer);

        Query query = parser.parse("(name = \"Herbert\") and (nachname = \"Maier\")");
        List<Person> filtered = query.filter(personList);

        Assert.assertTrue(filtered.contains(herbert1));
        Assert.assertFalse(filtered.contains(herbert2));
        Assert.assertFalse(filtered.contains(markus));
    }

    @Test
    public void testNameUNachnameAllesKlammern() throws ParseException {

        setup();

        de.feli490.utils.core.search.QueryParser parser = new de.feli490.utils.core.search.QueryParser(parameterIdentifierContainer);

        Query query = parser.parse("(name = \"Herbert\" and nachname = \"Grün\")");
        List<Person> filtered = query.filter(personList);

        Assert.assertFalse(filtered.contains(herbert1));
        Assert.assertTrue(filtered.contains(herbert2));
        Assert.assertFalse(filtered.contains(markus));
    }

    @Test(expected = ParseException.class)
    public void testInvalidQuery() throws ParseException {

        setup();

        de.feli490.utils.core.search.QueryParser parser = new de.feli490.utils.core.search.QueryParser(parameterIdentifierContainer);

        Query query = parser.parse("name = \"Herbert");
    }

    private record Person(StringParameterValue name, StringParameterValue nachname) implements ParameterValueContainer {
    
            public String vollerName() {
                return name.getValue() + " " + nachname.getValue();
            }
    
            @Override
            public String toString() {
                return "Person{" + "name=" + name + ", nachname=" + nachname + '}';
            }
    
            @Override
            public Collection<ParameterValue<?, ?>> getParameterValues() {
                return Arrays.asList(name, nachname);
            }
        }
}
