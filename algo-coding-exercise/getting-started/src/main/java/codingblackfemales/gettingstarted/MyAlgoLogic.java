package codingblackfemales.gettingstarted;

import codingblackfemales.action.Action;
import codingblackfemales.action.CancelChildOrder;
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
        final var activeChildOrders = state.getActiveChildOrders();
        // final var filledChildOrders = childOrders.get(1).getFilledQuantity();

        // logger.info("[MYALGO] The number of child orders is: " + sizeOfChildOrders);

        /********
         *
         * Add your logic here....
         * Pseudo code:
         * - DONE - check the market order book
         * - DONE - Check how many child orders exist
         * - DONE - create a new order if there are no child orders
         * - if there are child orders, check if they are filled
         * - if they are filled, create a new order
         * - if they are not filled, check if the price is still valid
         * - if the price is valid, do nothing
         * - if the price is not valid, cancel the order
         * 
         *
         */

        // change this to a limit to the number of child orders
        // if(childOrdersSize > 10){
        //     return NoAction.NoAction;
        // }

        
        logger.info("[MYALGO] The number of active child orders is: " + activeChildOrders.size());
        // logger.info("[MYALGO] The filled child orders: " );

        if(activeChildOrders.size() < 3){
            logger.info("[MYALGO] No child orders exist. Start by creating new order");
            BidLevel level = state.getBidAt(0);
            var price = level.price;
            var quantity = level.quantity;
            logger.info("[MYALGO] bid level is: " + level);
            return new CreateChildOrder(Side.BUY, quantity, price); 
        }else{
            final var option = activeChildOrders.stream().findFirst();

            if(option.isPresent()){
                var childOrderOption = option.get();
                BidLevel bestBidLevel = state.getBidAt(0);
                // var childOrderPrice = childOrderOption.getPrice();

                if(bestBidLevel == null){
                    logger.info("[MYALGO] No bid levels exist. Cancel the order");
                    return NoAction.NoAction;
                }

                if(childOrderOption.getPrice() == bestBidLevel.price){
                    logger.info("[MYALGO] Active child order price is still valid. Do nothing");
                    return NoAction.NoAction;
                }
                
                logger.info("[MYALGO] Active child order is (option): " + childOrderOption);
                return new CancelChildOrder(childOrderOption);
            }
        }
        // logger.info("[MYALGO] child orders filled: " + filledChildOrders);
        return NoAction.NoAction;
    }
}
