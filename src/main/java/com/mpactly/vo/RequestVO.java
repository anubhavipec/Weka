package com.mpactly.vo;

public class RequestVO {

	
		private String filePath;

		public String getFilePath() {
			return filePath;
		}

		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("RequestVO [filePath=");
			builder.append(filePath);
			builder.append("]");
			return builder.toString();
		}

		
		
		
}
