package com.somethingnifty.diablo3armory.activity.handlers.heroDetailsActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.somethingnifty.diablo3armory.R;
import com.somethingnifty.diablo3armory.domain.ActiveHero;
import com.somethingnifty.diablo3armory.domain.Stats;
import com.somethingnifty.diablo3armory.domain.enums.HeroType;
import com.somethingnifty.diablo3armory.domain.enums.StatDoubleType;
import com.somethingnifty.diablo3armory.domain.enums.StatIntegerType;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AttributesScreenFragment extends Fragment {
    private static final String ACTIVE_HERO_BUNDLE_ENTRY = "activeHero";

    private static final int TWO_DECIMAL_PLACES = 2;
    private static final int PERCENT_MULTIPLIER = 100;

    private ActiveHero activeHero;
    private View parentView;

    public static AttributesScreenFragment newInstance(ActiveHero activeHero) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ACTIVE_HERO_BUNDLE_ENTRY, activeHero);

        AttributesScreenFragment fragment = new AttributesScreenFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activeHero = (ActiveHero) getArguments().getSerializable(ACTIVE_HERO_BUNDLE_ENTRY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_attributes_screen, container, false);

        Stats stats = activeHero.getStats();

        processBaseSection(stats);
        processOffenseSection(stats);
        processDefenseSection(stats);
        processLifeSection(stats);
        processAdventureSection(stats);

        return parentView;
    }
    private void processBaseSection(Stats stats) {
        setTextViewText(R.id.strength_value, stats.getStat(StatIntegerType.STRENGTH).toString());
        setTextViewText(R.id.dexterity_value, stats.getStat(StatIntegerType.DEXTERITY).toString());
        setTextViewText(R.id.intelligence_value, stats.getStat(StatIntegerType.INTELLIGENCE).toString());
        setTextViewText(R.id.vitality_value, stats.getStat(StatIntegerType.VITALITY).toString());
        setTextViewText(R.id.life_value, stats.getStat(StatIntegerType.LIFE).toString());
        processResources(stats);
    }

    private void processResources(Stats stats) {
        HeroType heroType = activeHero.getHeroType();

        setTextViewText(R.id.primary_resource_value, stats.getStat(StatIntegerType.PRIMARY_RESOURCE).toString());

        if (HeroType.BARBARIAN.equals(heroType)){
            setTextViewText(R.id.primary_resource_header, R.string.fury_resource_string);
        }
        else if (HeroType.CRUSADER.equals(heroType)){
            setTextViewText(R.id.primary_resource_header, R.string.wrath_resource_string);
        }
        else if (HeroType.DEMON_HUNTER.equals(heroType)){
            processDemonHunterSpecificResources(stats);
        }
        else if (HeroType.MONK.equals(heroType)){
            setTextViewText(R.id.primary_resource_header, R.string.spirit_resource_string);
        }
        else if (HeroType.WITCH_DOCTOR.equals(activeHero.getHeroType())){
            setTextViewText(R.id.primary_resource_header, R.string.mana_resource_string);
        }
        else { //WIZARD
            setTextViewText(R.id.primary_resource_header, R.string.arcane_power_resource_string);
        }
    }

    private void processDemonHunterSpecificResources(Stats stats) {
        setTextViewText(R.id.primary_resource_header, R.string.hatred_resource_string);

        parentView.findViewById(R.id.secondary_resource_grouping).setVisibility(View.VISIBLE);
        setTextViewText(R.id.secondary_resource_header, R.string.discipline_resource_string);

        setTextViewText(R.id.secondary_resource_value, stats.getStat(StatIntegerType.SECONDARY_RESOURCE).toString());
    }

    private void setTextViewText(int idTextViewToChange, String text){
        TextView textView = (TextView) parentView.findViewById(idTextViewToChange);
        textView.setText(text);
    }

    private void setTextViewText(int idTextViewToChange, int idStringResource){
        TextView textView = (TextView) parentView.findViewById(idTextViewToChange);
        String string = getResources().getString(idStringResource);

        textView.setText(string);
    }

    private void processOffenseSection(Stats stats){
        setTextViewText(R.id.attack_speed_value, doubleStatToString(stats.getStat(StatDoubleType.ATTACK_SPEED), TWO_DECIMAL_PLACES));
        setTextViewText(R.id.crit_chance_value, percentStatToString(stats.getStat(StatDoubleType.CRIT_CHANCE), TWO_DECIMAL_PLACES));
        setTextViewText(R.id.crit_damage_value, percentStatToString(stats.getStat(StatDoubleType.CRIT_DAMAGE), TWO_DECIMAL_PLACES));
    }

    private String doubleStatToString(Double stat, int places) {
        if (places < 0){
            throw new IllegalArgumentException();
        }

        BigDecimal bd = BigDecimal.valueOf(stat);
        bd = bd.setScale(TWO_DECIMAL_PLACES, RoundingMode.HALF_UP);
        return bd.toString();
    }

    private String percentStatToString(Double stat, int places) {
        stat = stat * PERCENT_MULTIPLIER;
        return doubleStatToString(stat, places) + "%";
    }

    private void processDefenseSection(Stats stats) {
        setTextViewText(R.id.armor_value, stats.getStat(StatIntegerType.ARMOR).toString());
        processBlockAmount(stats);
        setTextViewText(R.id.armor_value, stats.getStat(StatIntegerType.ARMOR).toString());
        setTextViewText(R.id.block_chance_value, percentStatToString(stats.getStat(StatDoubleType.BLOCK_CHANCE), TWO_DECIMAL_PLACES));
        setTextViewText(R.id.physical_resist_value, stats.getStat(StatIntegerType.PHYSICAL_RESIST).toString());
        setTextViewText(R.id.cold_resist_value, stats.getStat(StatIntegerType.COLD_RESIST).toString());
        setTextViewText(R.id.fire_resist_value, stats.getStat(StatIntegerType.FIRE_RESIST).toString());
        setTextViewText(R.id.lightning_resist_value, stats.getStat(StatIntegerType.LIGHTNING_RESIST).toString());
        setTextViewText(R.id.poison_resist_value, stats.getStat(StatIntegerType.POISON_RESIST).toString());
        setTextViewText(R.id.arcane_holy_resist_value, stats.getStat(StatIntegerType.ARCANE_HOLY_RESIST).toString());
        setTextViewText(R.id.damage_reduction_value, percentStatToString(stats.getStat(StatDoubleType.DAMAGE_REDUCTION), TWO_DECIMAL_PLACES));
        setTextViewText(R.id.thorns_value, doubleStatToString(stats.getStat(StatDoubleType.THORNS), TWO_DECIMAL_PLACES));

    }

    private void processBlockAmount(Stats stats) {
        String blockAmountMin = stats.getStat(StatIntegerType.BLOCK_AMOUNT_MIN).toString();
        String blockAmountMax = stats.getStat(StatIntegerType.BLOCK_AMOUNT_MAX).toString();
        String blockAmount = blockAmountMin + " - " + blockAmountMax;

        setTextViewText(R.id.block_amount_value, blockAmount);
    }

    private void processLifeSection(Stats stats) {
        setTextViewText(R.id.life_on_hit_value, doubleStatToString(stats.getStat(StatDoubleType.LIFE_ON_HIT), TWO_DECIMAL_PLACES));
        setTextViewText(R.id.life_per_kill_value, doubleStatToString(stats.getStat(StatDoubleType.LIFE_PER_KILL), TWO_DECIMAL_PLACES));
        setTextViewText(R.id.life_steal_value, percentStatToString(stats.getStat(StatDoubleType.LIFE_STEAL), TWO_DECIMAL_PLACES));
    }

    private void processAdventureSection(Stats stats) {
        setTextViewText(R.id.gold_find_value, percentStatToString(stats.getStat(StatDoubleType.GOLD_FIND), TWO_DECIMAL_PLACES));
        setTextViewText(R.id.magic_find_value, percentStatToString(stats.getStat(StatDoubleType.MAGIC_FIND), TWO_DECIMAL_PLACES));
    }
}
