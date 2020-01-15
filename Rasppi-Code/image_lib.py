import numpy as np
import cv2
import sys
cap = cv2.VideoCapture(0)
def _color_framing(frames,l_h,l_s,l_v,u_h,u_s,u_v):
    frame=frames
    hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)
    lower=np.array([l_h,l_s,l_v])
    upper=np.array([u_h,u_s,u_v])
    mask=cv2.inRange(hsv,lower,upper)
    frame=cv2.bitwise_and(frame,frame,mask=mask)
    kernel = np.ones((5,5), np.uint8) 
    frame=cv2.erode(frame, kernel, iterations=1)
    return frame

def detectcircle(l_h,l_s,l_v,u_h,u_s,u_v,d_p,m_d):
    global cap
    _, frames = cap.read()
    frame=_color_framing(frames,l_h,l_s,l_v,u_h,u_s,u_v)
    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    blur = cv2.GaussianBlur(gray,(5,5),0)
    _, thresh_img = cv2.threshold(blur,91,255,cv2.THRESH_BINARY)
    h, w = thresh_img.shape
    circles = cv2.HoughCircles(thresh_img, cv2.HOUGH_GRADIENT, d_p, m_d)
    #each circle is (x, y, r)
    #return format is [[circle, circle, ...], [w, h]]
    return [circles, [w, h]]
def detectocta(l_h,l_s,l_v,u_h,u_s,u_v):
    global cap
    _, frames = cap.read()
    frame = _color_framing(frames, l_h, l_s, l_v, u_h, u_s, u_v)
    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    blur = cv2.GaussianBlur(gray,(5,5),0)
    _, thresh_img = cv2.threshold(blur,91,255,cv2.THRESH_BINARY)
    _, contours, _ = cv2.findContours(thresh_img, cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)
    for cont in contours:
        area = cv2.contourArea(cont)
        if area > 400:
            approx = cv2.approxPolyDP(cont, 0.009 * cv2.arcLength(cont, True), True)
            if (len(approx) == 8):
                return approx
                #test test test