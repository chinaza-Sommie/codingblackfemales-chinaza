package codingblackfemales.gettingstarted;

import codingblackfemales.action.Action;
import codingblackfemales.action.CreateChildOrder;
import codingblackfemales.action.NoAction;
import codingblackfemales.algo.AlgoLogic;
import codingblackfemales.sotw.SimpleAlgoState;
import codingblackfemales.util.Util;
import messages.order.Side;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import codingblackfemales.sotw.marketdata.BidLevel;

public class MyAlgoLogic implements AlgoLogic {

    private static final Logger logger = LoggerFactory.getLogger(MyAlgoLogic.class);

    @Override
    public Action evaluate(SimpleAlgoState state) {

        var orderBookAsString = Util.orderBookToString(state);

        logger.info("[MYALGO] The state of the order book is:\n" + orderBookAsString);

        var childOrders = state.getChildOrders();
        var childOrdersSize = childOrders.size();

        // logger.info("[MYALGO] The number of child orders is: " + sizeOfChildOrders);

        /********
         *
         * Add your logic here....
         * Pseudo code:
         * - check the market order book
         * - Check how many child orders exist
         * - create a new order if there are no child orders
         * - if there are child orders, check if they are filled
         * - if they are filled, create a new order
         * - if they are not filled, check if the price is still valid
         * - if the price is valid, do nothing
         * - if the price is not valid, cancel the order
         * 
         *
         */

        if(childOrdersSize > 10){
            return NoAction.NoAction;
        }

        logger.info("[MYALGO] The number of child orders is: " + childOrdersSize);

        if(childOrdersSize == 0){
            logger.info("[MYALGO] No child orders exist. Start by creating new order");
            BidLevel level = state.getBidAt(0);
            var price = level.price;
            var quantity = level.quantity;
;           logger.info("[MYALGO] bid level is: " + level);
            return new CreateChildOrder(Side.BUY, quantity, price); 
        }else{
            
        }

        logger.info("[MYALGO] The number of child orders is: " + childOrdersSize);
        return NoAction.NoAction;
    }
}
