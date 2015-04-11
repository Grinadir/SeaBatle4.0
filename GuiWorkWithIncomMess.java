package sample;

/**
 * Created by User on 10.04.2015.
 */
public class GuiWorkWithIncomMess {

    private ClientServerConnector connector;
    private Gui gui;

    public GuiWorkWithIncomMess(Gui gui, ClientServerConnector connector) {
        this.connector=connector;
        this.gui=gui;
    }

    public void main() {

        String tempString=connector.getMessage();

        try {
            if (tempString.charAt(0) != '!' && tempString.charAt(0) != '#') {
                gui.setTextInCommonChat(connector);

            }
            if (tempString.charAt(0) == '#') {
                gui.setTextInCommonChat(connector);
                int dX = Integer.parseInt(tempString.substring(tempString.indexOf("$") + 1, tempString.indexOf("%")));
                int dY = Integer.parseInt(tempString.substring(tempString.indexOf("%") + 1, tempString.indexOf("*")));
                //Сначала должна идти функция workWithMyField, затем SendingResultOfFire()
                new GuiWorkWithMyField(gui).main(dX, dY);
                System.out.println("Before new SendingResultOfFire(this, connector).sendResult("+dX+", "+dY+")");
                new SendingResultOfFire(gui, connector).sendResult(dX, dY);
                System.out.println("After new SendingResultOfFire(this, connector).sendResult("+dX+", "+dY+")");

            }
            if (tempString.charAt(0) == '!') {

                gui.setTextInCommonChat(connector);
                int index1=400;
                int index2=440;
                int index3=440;
                int index4=440;

                String result;
                int dX = Integer.parseInt(tempString.substring(tempString.indexOf("$") + 1, tempString.indexOf("%")));
                int dY = Integer.parseInt(tempString.substring(tempString.indexOf("%") + 1, tempString.indexOf("*")));
                result = tempString.toString().substring(tempString.indexOf("*") + 1, tempString.indexOf(";"));
                if(result.equals("DESTROY")) {
                    index1 = Integer.parseInt(tempString.substring(tempString.indexOf(";") + 1, tempString.indexOf("&")));
                    index2 = Integer.parseInt(tempString.substring(tempString.indexOf("&") + 1, tempString.indexOf("@")));
                    index3 = Integer.parseInt(tempString.substring(tempString.indexOf("@") + 1, tempString.indexOf("#")));
                    index4 = Integer.parseInt(tempString.substring(tempString.indexOf("#") + 1, tempString.indexOf("~")));
                }
                new GuiWorkWithEnemyField(gui).main(dX, dY, result, index1, index2, index3, index4);

            }
        } catch (StringIndexOutOfBoundsException e) {


        } catch (Exception e) {

        }
    }
}
