package sample;

/**
 * Created by User on 10.04.2015.
 */
public class GuiWorkWithIncomingMessage {

    private ClientServerConnector connector;
    private Engine engine;

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
                //Сначала должна идти функция workWithMyField, затем SendingResultOfFire()
                new GuiWorkWithMyField(engine.getRects()).main(dX, dY);
                System.out.println("Before new SendingResultOfFire(this, connector).sendResult(" + dX + ", " + dY + ")");
                new SendingResultOfFire(engine, connector).sendResult(dX, dY);
                System.out.println("After new SendingResultOfFire(this, connector).sendResult(" + dX + ", " + dY + ")");
            }
            if (tempString.charAt(0) == '!') {

                int index1 = 400;
                int index2 = 440;
                int index3 = 440;
                int index4 = 440;
                String result;
                int dX = parse(tempString, '$', '%');
                int dY = parse(tempString, '%', '*');
                System.out.println("dX " + dX + ", dY " + dY);
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
        } catch (Exception e) {
        }
    }

    //Extract function

    private int parse(String temp, char n1, char n2) {
        return Integer.parseInt(temp.substring(temp.indexOf(n1) + 1, temp.indexOf(n2)));
    }


}
