package decorator.MP04.deco;

import decorator.MP04.Display;
import decorator.MP04.LabelPanel;

import javax.swing.*;
import java.awt.*;

public abstract class InfoDisplayDecorator extends Display {

    JPanel panel;
    LabelPanel labelPanel;
    Display display;

    public InfoDisplayDecorator(Display display, int width, int height) {
        super(width, height);
        this.display = display;
    }

    @Override
    public JPanel create() {
        // 레이블을 담을 패널 생성
        panel = new JPanel();
        // 레이블 또는 다른 패널이 추가될 때 세로 방향으로 나열되도록 레이아웃 설정
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        // 최소 크기와 선호하는 크기를 동일하게 만들어서 패널 너비와 높이를 고정 시킴
        panel.setMinimumSize(new Dimension(getWidth(), getHeight()));
        panel.setPreferredSize(new Dimension(getWidth(), getHeight()));
        panel.add(display.create());
        // 문자열을 보일 레이블 생성
        labelPanel = new LabelPanel();
        // 패널에 레이블을 붙임.
        panel.add(labelPanel.createPanel(getWidth(), getHeight()));
        return panel;
    }

    @Override
    public void show() {
        display.show();
        labelPanel.updateText(getDisplayTest());
    }

    public abstract String getDisplayTest();

    @Override
    public int getHeight(){
        return display.getHeight() + super.getHeight();
    }
}
