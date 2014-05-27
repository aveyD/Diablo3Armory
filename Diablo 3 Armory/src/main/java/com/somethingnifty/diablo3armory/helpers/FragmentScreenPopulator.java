package com.somethingnifty.diablo3armory.helpers;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.somethingnifty.diablo3armory.R;
import com.somethingnifty.diablo3armory.domain.Act;
import com.somethingnifty.diablo3armory.domain.enums.QuestType;

import java.util.Map;

public class FragmentScreenPopulator {
    private Resources resources;
    private FragmentActivity activity;

    public FragmentScreenPopulator(Resources resources, FragmentActivity activity){
        this.resources = resources;
        this.activity = activity;
    }

    public LinearLayout populateProgressionLayout(Act act) {

        LinearLayout actSectionLayout = getMainContentLayout();

        RelativeLayout actHeaderTextLayout = getActHeaderLayout(act);
        actSectionLayout.addView(actHeaderTextLayout);

        View headerHorizontalBar = getHeaderHorizontalBar();
        actSectionLayout.addView(headerHorizontalBar);

        for(Map.Entry<QuestType, Boolean> entry : act.getQuestCompletionByQuestType().entrySet()){
            RelativeLayout questTextLayout = new RelativeLayout(activity);

            TextView questText = getQuestName(entry.getKey());
            questTextLayout.addView(questText);

            TextView completedText = getQuestCompleted(entry.getValue());
            questTextLayout.addView(completedText);

            actSectionLayout.addView(questTextLayout);
        }

        return actSectionLayout;
    }

    private  LinearLayout getMainContentLayout() {
        LinearLayout.LayoutParams actHeaderLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        int margins = (int) resources.getDimension(R.dimen.main_content_layout_margin_size);
        actHeaderLayoutParams.setMargins(margins, margins, margins, margins);

        LinearLayout actHeaderLayout = new LinearLayout(activity);
        actHeaderLayout.setOrientation(LinearLayout.VERTICAL);

        actHeaderLayout.setLayoutParams(actHeaderLayoutParams);

        return actHeaderLayout;
    }

    private RelativeLayout getActHeaderLayout(Act act) {
        RelativeLayout actHeaderTextLayout = new RelativeLayout(activity);

        TextView headerText = getActName(act);
        actHeaderTextLayout.addView(headerText);

        TextView headerTextCompleted = getActCompleted(act);
        actHeaderTextLayout.addView(headerTextCompleted);

        return actHeaderTextLayout;
    }

    private TextView getActName(Act act) {
        RelativeLayout.LayoutParams headerTextParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        headerTextParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);

        TextView headerText = new TextView(activity);
        headerText.setLayoutParams(headerTextParams);
        headerText.setTypeface(null, Typeface.BOLD);
        headerText.setTextSize(TypedValue.COMPLEX_UNIT_SP, resources.getDimension(R.dimen.info_header_text_size));

        headerText.setText(act.getActType().getToString());

        return headerText;
    }

    private TextView getActCompleted(Act act) {
        RelativeLayout.LayoutParams headerTextCompletedParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        headerTextCompletedParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);

        TextView headerTextCompleted = new TextView(activity);
        headerTextCompleted.setLayoutParams(headerTextCompletedParams);
        headerTextCompleted.setTypeface(null, Typeface.BOLD);
        headerTextCompleted.setTextSize(resources.getDimension(R.dimen.info_header_text_size));

        String actCompletedString = act.isCompleted()
                ? resources.getString(R.string.progression_completed_header)
                : resources.getString(R.string.progression_not_completed_header);
        headerTextCompleted.setText(actCompletedString);

        return headerTextCompleted;
    }

    private View getHeaderHorizontalBar() {
        LinearLayout.LayoutParams horizontalBarLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) resources.getDimension(R.dimen.horizontal_bar_header_height));
        int horizontalBarMargin = (int) resources.getDimension(R.dimen.horizontal_bar_margin);
        horizontalBarLayout.setMargins(0, horizontalBarMargin, 0, horizontalBarMargin);

        View headerHorizontalBar = new View(activity);
        headerHorizontalBar.setLayoutParams(horizontalBarLayout);
        headerHorizontalBar.setBackgroundColor(resources.getColor(R.color.horizontal_rule_grey));

        return headerHorizontalBar;
    }

    private TextView getQuestName(QuestType questType) {
        RelativeLayout.LayoutParams questTextParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        questTextParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);

        TextView questText = new TextView(activity);
        questText.setLayoutParams(questTextParams);
        questText.setText(questType.getQuestName());

        return questText;
    }

    private  TextView getQuestCompleted(Boolean isQuestCompleted) {
        RelativeLayout.LayoutParams questCompletedParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        questCompletedParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);

        TextView completedText = new TextView(activity);
        completedText.setLayoutParams(questCompletedParams);


        int questCompletedColor = isQuestCompleted
                                    ? resources.getColor(R.color.green)
                                    : resources.getColor(R.color.red);
        completedText.setTextColor(questCompletedColor);

        String questCompletedString = isQuestCompleted
                ? resources.getString(R.string.progression_completed_quest)
                : resources.getString(R.string.progression_not_completed_quest);
        completedText.setText(questCompletedString);

        return completedText;
    }
}
