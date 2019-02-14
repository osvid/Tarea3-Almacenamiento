package uv.edu.tarea3;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.test.suitebuilder.annotation.LargeTest;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.util.ArrayList;

import uv.edu.customlog.InfoLog;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */


class Datos {
    private String nombre;
    private int dobles;
    private int ganadores;
    private double porcentaje_primeros;
    private int errores_no_forzados;
    private double porcenctaje_breakpoints_ganados;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDobles() {
        return dobles;
    }

    public void setDobles(int dobles) {
        this.dobles = dobles;
    }

    public int getGanadores() {
        return ganadores;
    }

    public void setGanadores(int ganadores) {
        this.ganadores = ganadores;
    }

    public double getPorcentaje_primeros() {
        return porcentaje_primeros;
    }

    public void setPorcentaje_primeros(double porcentaje_primeros) {
        this.porcentaje_primeros = porcentaje_primeros;
    }

    public int getErrores_no_forzados() {
        return errores_no_forzados;
    }

    public void setErrores_no_forzados(int errores_no_forzados) {
        this.errores_no_forzados = errores_no_forzados;
    }

    public double getPorcenctaje_breakpoints_ganados() {
        return porcenctaje_breakpoints_ganados;
    }

    public void setPorcenctaje_breakpoints_ganados(double porcenctaje_breakpoints_ganados) {
        this.porcenctaje_breakpoints_ganados = porcenctaje_breakpoints_ganados;
    }

    @Override
    public String toString() {
        return "Datos{" +
                "nombre='" + nombre + '\'' +
                ", dobles=" + dobles +
                ", ganadores=" + ganadores +
                ", porcentaje_primeros=" + porcentaje_primeros +
                ", errores_no_forzados=" + errores_no_forzados +
                ", porcenctaje_breakpoints_ganados=" + porcenctaje_breakpoints_ganados +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Datos datos = (Datos) o;

        if (getDobles() != datos.getDobles()) return false;
        if (getGanadores() != datos.getGanadores()) return false;
        if (Double.compare(datos.getPorcentaje_primeros(), getPorcentaje_primeros()) != 0)
            return false;
        if (getErrores_no_forzados() != datos.getErrores_no_forzados()) return false;
        if (Double.compare(datos.getPorcenctaje_breakpoints_ganados(), getPorcenctaje_breakpoints_ganados()) != 0)
            return false;
        return getNombre().equals(datos.getNombre());

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getNombre().hashCode();
        result = 31 * result + getDobles();
        result = 31 * result + getGanadores();
        temp = Double.doubleToLongBits(getPorcentaje_primeros());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getErrores_no_forzados();
        temp = Double.doubleToLongBits(getPorcenctaje_breakpoints_ganados());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ApplicationTest {
    private static long PAUSE = 1000;


    public static void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception ex) {
        }
    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Rule
    public ActivityTestRule<PreferencesActivity> mPrefsRule = new ActivityTestRule(PreferencesActivity.class);


    private UiDevice mDevice;
    private String name;
    private String numero_sets = "3";
    private String numero_juegos_set = "4";
    private boolean punto_oro = true;

    @Before
    public void setUp() throws Exception {
        Instrumentation ins = InstrumentationRegistry.getInstrumentation();
        mDevice = UiDevice.getInstance(ins);
        name = mActivityRule.getActivity().getBaseContext().getResources().getString(R.string.app_name);
    }

    @Test
    public void test1() throws Exception {
        MainActivity ma = (MainActivity) mActivityRule.getActivity();
        ArrayList<String> estadisticas = new ArrayList<String>();

        Datos d1 = new Datos();
        d1.setNombre("Antonio");
        d1.setDobles(10);
        d1.setErrores_no_forzados(20);
        d1.setGanadores(5);
        d1.setPorcenctaje_breakpoints_ganados(20.5);
        d1.setPorcentaje_primeros(52.6);


        Datos d2 = new Datos();
        d2.setNombre("Pedro");
        d2.setDobles(5);
        d2.setErrores_no_forzados(10);
        d2.setGanadores(15);
        d2.setPorcenctaje_breakpoints_ganados(80.3);
        d2.setPorcentaje_primeros(70.6);

        GsonBuilder gb = new GsonBuilder();
        Gson g = gb.create();
        String est1 = g.toJson(d1, Datos.class);
        String est2 = g.toJson(d2, Datos.class);

        estadisticas.add(est1);
        estadisticas.add(est2);

        ma.setEstadisticas(estadisticas);

        ApplicationTest.pause(PAUSE);

        onView(withId(R.id.boton1)).perform(click());

        ApplicationTest.pause(PAUSE);

        File f = mActivityRule.getActivity().getExternalFilesDir(null);
        String file = f.getAbsolutePath() + "/" + MainActivity.fichero;

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String cad;
            cad = br.readLine();
            Datos aux = g.fromJson(new StringReader(cad), Datos.class);
            InfoLog.log(name, "Test 1: " + aux.equals(d1) + ", " + aux.toString());
            cad = br.readLine();
            aux = g.fromJson(new StringReader(cad), Datos.class);
            InfoLog.log(name, "Test 1: " + aux.equals(d2) + ", " + aux.toString());
            br.close();
        } catch (Exception ex) {
        }
    }

    @Test
    public void test2() throws Exception {
        onView(withId(R.id.action_settings)).perform(click());

        ApplicationTest.pause(PAUSE);

        onView(withId(R.id.et_num_sets)).perform(clearText());
        onView(withId(R.id.et_num_sets)).perform(typeText(numero_sets));
        ApplicationTest.pause(PAUSE);
        onView(withId(R.id.et_juegos_set)).perform(clearText());
        onView(withId(R.id.et_juegos_set)).perform(typeText(numero_juegos_set), closeSoftKeyboard());
        ApplicationTest.pause(PAUSE);

        CheckBox cb = (CheckBox) mPrefsRule.getActivity().findViewById(R.id.cb_punto_oro);
        if (!(cb.isChecked() == punto_oro))
            onView(withId(R.id.cb_punto_oro)).perform(click());

        onView(withId(R.id.boton2)).perform(click());

        InfoLog.log(name, "Test2: Preferencias almacenadas");

        ApplicationTest.pause(100);

        mDevice.pressBack();
    }

    @Test
    public void test3() throws Exception {

        onView(withId(R.id.action_settings)).perform(click());

        ApplicationTest.pause(PAUSE);

        EditText t1 = (EditText) mPrefsRule.getActivity().findViewById(R.id.et_num_sets);
        EditText t2 = (EditText) mPrefsRule.getActivity().findViewById(R.id.et_juegos_set);
        CheckBox cb = (CheckBox) mPrefsRule.getActivity().findViewById(R.id.cb_punto_oro);

        InfoLog.log(name, "Test3: " + t1.getText().toString() + ", " + t2.getText().toString() + ", " + cb.isChecked());

        ApplicationTest.pause(100);
        mDevice.pressHome();
    }
}