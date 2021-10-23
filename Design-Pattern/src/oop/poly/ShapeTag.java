package oop.poly;

public class ShapeTag {
    private String tag;

    public ShapeTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString(){
        return "#" + tag;
    }
}

class RectangleTag extends ShapeTag{
    private String rectangleTag;

    public RectangleTag(String tag, String rectangleTag) {
        super(tag);
        this.rectangleTag = rectangleTag;
    }

    @Override
    public String toString(){
        return "#" + rectangleTag + " " + super.toString(); // #tag 가져옴
    }

    public String getRectangleTag() {
        return rectangleTag;
    }
}

class CircleTag extends ShapeTag{
    private String circleTag;

    public CircleTag(String tag, String rectangleTag) {
        super(tag);
        this.circleTag = rectangleTag;
    }

    @Override
    public String toString(){
        return "#" + circleTag + " " + super.toString(); // #tag 가져옴
    }

    public String getRectangleTag() {
        return circleTag;
    }
}