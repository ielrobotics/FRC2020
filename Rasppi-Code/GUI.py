import numpy as np
import image_lib
import time
import cv2
image_lib.init()
def nothing(x):
  pass
cv2.namedWindow("Trackbars")
cv2.createTrackbar("L-H","Trackbars",0,255,nothing)
cv2.createTrackbar("L-S","Trackbars",0,255,nothing)
cv2.createTrackbar("L-V","Trackbars",0,255,nothing)
cv2.createTrackbar("U-H","Trackbars",255,255,nothing)
cv2.createTrackbar("U-S","Trackbars",255,255,nothing)
cv2.createTrackbar("U-V","Trackbars",255,255,nothing)

while(True):
  time.sleep(0.04)
  # Capture frame-by-frame
  ret, frame = image_lib.get_cam_frame()
  l_h=cv2.getTrackbarPos("L-H","Trackbars")
  l_s=cv2.getTrackbarPos("L-S","Trackbars")
  l_v=cv2.getTrackbarPos("L-V","Trackbars")
  u_h=cv2.getTrackbarPos("U-H","Trackbars")
  u_s=cv2.getTrackbarPos("U-S","Trackbars")
  u_v=cv2.getTrackbarPos("U-V","Trackbars")
  
  # Our operations on the frame come here
  frame=image_lib.detectocta_from_frame(frame,l_h,l_s,l_v,u_h,u_s,u_v)
  #contours =  cv2.findContours(thresh_img,cv2.RETR_TREE,cv2.CHAIN_APPROX_SIMPLE)[-2]
  #x1,y1=0,0
  #for c in contours:
  #  approx = cv2.approxPolyDP(c, 0.01* cv2.arcLength(c, True), True)
  #  x1 += approx.ravel()[0]
  #  y1 += approx.ravel()[1]
  #cv2.drawContours(frame, contours, -1, (0,255,0), 3)
  #if len(contours)==0:
  #  pass
  #else:
  #  x1=x1/len(contours)
  #  y1=y1/len(contours)
  #frame=cv2.circle(frame,(int(x1)+10,int(y1)+10),10,(0,0,255),5)
  cv2.imshow('frame',frame)
  #cv2.waitKey(0)
  #sleep(0.2)
  #if cv2.waitKey(1) & 0xFF == ord('q'):
  #  break
  cv2.waitKey(0)
  print("INTERVAL")

# When everything done, release the capture
cv2.destroyAllWindows()
