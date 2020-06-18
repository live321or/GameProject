package com.samoylov.gameproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.samoylov.gameproject.character.heroes.Hero;
import com.samoylov.gameproject.authorization.ApiClient;
import com.samoylov.gameproject.authorization.ApiInterface;
import com.samoylov.gameproject.authorization.LoginFragment;
import com.samoylov.gameproject.authorization.PrefConfig;
import com.samoylov.gameproject.authorization.RegistrationFragment;
import com.samoylov.gameproject.authorization.User;
import com.samoylov.gameproject.locations.Location;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnLoginFromActivityListener {
    EditText name;
    Button create;
    public static PrefConfig prefConfig;
    public static ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        prefConfig = new PrefConfig(this);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            if (prefConfig.readLoginStatus()) {
                Call<User> call = apiInterface.your_name(prefConfig.readCookie());
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.code() == 200) {
                            Hero hero=new Hero(response.body().getName(),
                                    1000,response.body().getStr(),response.body().getAgi(),
                                    response.body().getInt(),response.body().getLuc(),
                                    response.body().getLvl(),response.body().getLocation(),
                                    response.body().getHp(),0);

                            hero.setLocation("Москва");
                            hero.setEXP(response.body().getEXP());


                            MainActivity.prefConfig.displayToast("ВАНДУФЛ " + response.body().getName() + " " +
                                    "1000" + " " + response.body().getStr() + " " + response.body().getAgi() + " " +
                                    response.body().getInt() + " " + response.body().getLuc() + " " +
                                    response.body().getLvl() + " " + "Москва" + " " +
                                    response.body().getHp() + " " + response.body().getEXP());

                            setCreate(hero);

                        } else if (response.code() == 500) {
                            MainActivity.prefConfig.displayToast("Server Error");
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable throwable) {

                    }
                });
            } else {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, new LoginFragment()).commit();
            }
        }
//        name=(EditText)findViewById(R.id.name);
//        create=(Button)findViewById(R.id.create);
    }


    public void setCreate(Hero hero) {

        Data.bdHeros.clear();
        Data.bdMob.clear();
        Data.bdLocations.clear();

        Location location1 = new Location("Москва", "Вы в центре мира");
        Location location2 = new Location("Замкадье", "Вы в лесу");
        Location location3 = new Location("Площадь Приакроувера", "");
        Location location4 = new Location("Ворота Приакроувера", "");
        Location location5 = new Location("Развилка у Приакроувера", "");
        Location location6 = new Location("Берег у реки Кита", "");
        Location location7 = new Location("Пещера Велонис", "");

        Data.bdLocations.add(location1);
        Data.bdLocations.add(location2);
        Data.bdLocations.add(location3);
        Data.bdLocations.add(location4);
        Data.bdLocations.add(location5);
        Data.bdLocations.add(location6);
        Data.bdLocations.add(location7);

//        final Hero hero = new Hero(string,20, 0, 0, 0, 0, 1, "Замкадье", 20, 0);
        Hero hero2 = new Hero("Настя", 10, 0, 0, 0, 0, 1, "Замкадье", 10, 0);
        hero2.setLocation("Замкадье");
        Hero hero3 = new Hero("Kirill", 20, 0, 0, 0, 0, 1, "Замкадье", 20, 0);
        hero3.setLocation("Замкадье");
        Hero hero4 = new Hero("Sasha", 10, 1, 2, 3, 0, 1, "Москва", 10, 0);
        hero4.setLocation("Москва");


        location1.addTransition("Замкадье");
        location1.addTransition("Дом");
//        location1.addDropList("5k RUB");

        location1.addOnLocation();
        location2.addTransition("Москва");
        location3.addTransition("Москва");
//        location2.addDropList("5 RUB");

        location1.addTransition("Площадь Приакроувера");
        location4.addTransition("Площадь Приакроувера");
        location3.addTransition("Ворота Приакроувера");


        location5.addTransition("Ворота Приакроувера");
        location4.addTransition("Развилка у Приакроувера");

        location6.addTransition("Развилка у Приакроувера");
        location7.addTransition("Развилка у Приакроувера");
        location5.addTransition("Пещера Велонис");
        location5.addTransition("Берег у реки Кита");

        location1.addOnLocation();
        location2.addOnLocation();
        location3.addOnLocation();
        location4.addOnLocation();
        location5.addOnLocation();
        location6.addOnLocation();
        location7.addOnLocation();
//        location3.addOnLocation();
//        location1.addTransition("Площадь Приакроувера");
//        location4.addOnLocation();
//        location3.addTransition("Ворота Приакроувера");
//        location5.addOnLocation();
//        location3.addTransition("Развилка у Приакроувера");
//        location6.addOnLocation();
//        location3.addTransition("Берег у реки Кита");
//        location7.addOnLocation();
//        location3.addTransition("Пещера Велонис");

//        hero.setLocation("Москва");

//        Data.bdHeros.add(hero);
        Data.bdHeros.add(0,hero);
        Data.bdHeros.add(hero2);
        Data.bdHeros.add(hero3);
        Data.bdHeros.add(hero4);

        Intent intent = new Intent(this, World.class);
//
//        Запрос данных героя (базвый класс)/////////////////////////////////////////////////////////////////////////////
//
//
//
        startActivity(intent);

        finish();
    }


    @Override
    public void performRegister() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new RegistrationFragment()).addToBackStack(null).commit();

    }

    @Override
    public void performLogin(Hero hero,String cookie) {

        prefConfig.writeCookie(cookie);


        setCreate(hero);
    }
}
