package space.lobanovi.m2_hw_7;

 public class CalculatorModel {
     private int firstArg;
     private int secondArg;

     private StringBuilder inputStr = new StringBuilder();

     private int actionSelected;

     private State state;

     private enum State {
         firstArgInput,
         operationSelected,
         secondArgInput,
         resultShow
     }

     public CalculatorModel() {
         state = State.firstArgInput;
     }

     public void onNumPressed(int buttonId) {

         if (state == State.resultShow) {
             state = State.firstArgInput;
             inputStr.setLength(0);
         }

         if (state == State.operationSelected) {
             state = State.secondArgInput;
             inputStr.setLength(0);
         }

         if (inputStr.length() < 9) {
             switch (buttonId) {
                 case R.id.btn0:
                     if (inputStr.length() != 0) {
                         inputStr.append("0");
                     }
                     break;
                 case R.id.btn1:
                     inputStr.append("1");
                     break;
                 case R.id.btn2:
                     inputStr.append("2");
                     break;
                 case R.id.btn3:
                     inputStr.append("3");
                     break;
                 case R.id.btn4:
                     inputStr.append("4");
                     break;
                 case R.id.btn5:
                     inputStr.append("5");
                     break;
                 case R.id.btn6:
                     inputStr.append("6");
                     break;
                 case R.id.btn7:
                     inputStr.append("7");
                     break;
                 case R.id.btn8:
                     inputStr.append("8");
                     break;
                 case R.id.btn9:
                     inputStr.append("9");
                     break;
             }
         }

     }

     public void onActionPressed(int actionId) {
         if (actionId == R.id.btnRavno && state == State.secondArgInput && inputStr.length() > 0) {
             secondArg = Integer.parseInt(inputStr.toString());
             state = State.resultShow;
             inputStr.setLength(0);
             switch (actionSelected) {
                 case R.id.btnPlus:
                     inputStr.append(firstArg + secondArg);
                     break;
                 case R.id.btnMinys:
                     inputStr.append(firstArg - secondArg);
                     break;
                 case R.id.btnX:
                     inputStr.append(firstArg * secondArg);
                     break;
                 case R.id.btnD:
                     inputStr.append(firstArg / secondArg);
                     break;
             }

         } else if (inputStr.length() > 0 && state == State.firstArgInput && actionId != R.id.btnRavno) {
             firstArg = Integer.parseInt(inputStr.toString());
             state = State.operationSelected;
             actionSelected = actionId;
         }
     }

    public String getText() {
         StringBuilder str = new StringBuilder();
         switch (state) {
             default:
                 return inputStr.toString();
             case operationSelected:
                 return str.append(firstArg).append(' ')
                         .append(getOperationChar())
                         .toString();
             case secondArgInput:
                 return str.append(firstArg).append(' ')
                         .append(getOperationChar())
                         .append(' ')
                         .append(inputStr)
                         .toString();
             case resultShow:
                 return str.append(firstArg).append(' ')
                         .append(getOperationChar())
                         .append(' ')
                         .append(secondArg)
                         .append(" = ")
                         .append(inputStr.toString())
                         .toString();
         }
     }

    private char getOperationChar() {
        switch (actionSelected) {
            case R.id.btnPlus:
                return '+';
            case R.id.btnMinys:
                 return '-';
                 case R.id.btnX:
                 return '*';
             case R.id.btnD:
             default:
                 return '/';

        }
     }

     public void reset() {
         state = State.firstArgInput;
         inputStr.setLength(0);
     }
 }

