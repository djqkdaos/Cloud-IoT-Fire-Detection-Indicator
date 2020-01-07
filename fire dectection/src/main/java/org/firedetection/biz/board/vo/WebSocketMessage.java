package org.firedetection.biz.board.vo;

public class WebSocketMessage {
		private String name;
	    public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		private String title;
	    private String message;
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public WebSocketMessage(String title, String message) {
			super();
			this.title = title;
			this.message = message;
		}
	    
	    
	  
}
