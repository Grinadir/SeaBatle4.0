package sample;

/**
 * Created by User on 10.04.2015.
 */
public class GuiWorkWithIncomingMessage {

    private ClientServerConnector connector;
    private Engine engine;
    private final int NON_INDEX=440;

    public GuiWorkWithIncomingMessage(Engine engine, final ClientServerConnector connector) {
        this.connector = connector;
        this.engine = engine;
    }

    public void main(String tempString) {
        try {
            if (tempString.charAt(0) != '!' && tempString.charAt(0) != '#') {

            }
            if (tempString.charAt(0) == '#') {

                int dX = parse(tempString, '$', '%');
                int dY = parse(tempString, '%', '*');
                System.out.println("dX " + dX + ", dY " + dY);
                //At the beginning must be workWithMyField, after SendingResultOfFire()
                new GuiWorkWithMyField(engine.getRects()).main(dX, dY);
                new SendingResultOfFire(engine, connector).sendResult(dX, dY);
            }
            if (tempString.charAt(0) == '!') {

                int index1 = NON_INDEX;
                int index2 = NON_INDEX;
                int index3 = NON_INDEX;
                int index4 = NON_INDEX;
                String result;
                int dX = parse(tempString, '$', '%');
                int dY = parse(tempString, '%', '*');
                result = tempString.toString().substring(tempString.indexOf("*") + 1, tempString.indexOf(";"));
                if (result.equals("MISS")) {
                    engine.getStatus().setFollowStep(false);
                }
                if (result.equals("DESTROY")) {
                    index1 = Integer.parseInt(tempString.substring(tempString.indexOf(";") + 1, tempString.indexOf("&")));
                    index2 = Integer.parseInt(tempString.substring(tempString.indexOf("&") + 1, tempString.indexOf("@")));
                    index3 = Integer.parseInt(tempString.substring(tempString.indexOf("@") + 1, tempString.indexOf("#")));
                    index4 = Integer.parseInt(tempString.substring(tempString.indexOf("#") + 1, tempString.indexOf("~")));
                }
                new GuiWorkWithEnemyField(engine.getRects()).main(dX, dY, result, index1, index2, index3, index4);
            }
        } catch (StringIndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int parse(String temp, char n1, char n2) {
        return Integer.parseInt(temp.substring(temp.indexOf(n1) + 1, temp.indexOf(n2)));
    }


}
