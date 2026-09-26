package codingblackfemales.gettingstarted;

import codingblackfemales.action.Action;
import codingblackfemales.action.NoAction;
import codingblackfemales.algo.AlgoLogic;
import codingblackfemales.sotw.SimpleAlgoState;
import codingblackfemales.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAlgoLogic implements AlgoLogic {

    private static final Logger logger = LoggerFactory.getLogger(MyAlgoLogic.class);

    @Override
    public Action evaluate(SimpleAlgoState state) {

        var orderBookAsString = Util.orderBookToString(state);

        logger.info("[MYALGO] The state of the order book is:\n" + orderBookAsString);

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

        return NoAction.NoAction;
    }
}
