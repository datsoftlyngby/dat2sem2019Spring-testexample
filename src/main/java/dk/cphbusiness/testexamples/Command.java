package dk.cphbusiness.testexamples;

import javax.servlet.http.HttpServletRequest;

public interface Command {
  String execute(HttpServletRequest request, LogicFacade logic);
  }
