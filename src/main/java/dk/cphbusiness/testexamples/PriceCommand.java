package dk.cphbusiness.testexamples;

import javax.servlet.http.HttpServletRequest;

public class PriceCommand implements Command {

  @Override
  public String execute(HttpServletRequest request, LogicFacade logic) {
    String itemCode = request.getParameter("code");
    double price = logic.getPrice(itemCode);
    request.setAttribute("price", price);
    return "pricepage.jsp";
    }
  
  }
