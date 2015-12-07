function [ varargout ] = rca1pc( pc, distanceBoundOnEdges )
%RCA1PC This function computes the 1-D persistence diagram of a point cloud
%pc with given distance bound.  It relies on the Java TDA set.  
%
%The output is both the 0 and 1-D persistence diagrams, as well as the number of edges
%and the number of columns in the reduction matrix:
%
%If only one object is captured in output, it is the 1-D diagram.  
%
%If two, they are the 1-D and 0-D diagrams.  
%
%If four, the are the 1-D and 0-D diagrams, followed by the number of edges
%and the number of columns in the reduction matrix.

%Written by Rann Bar-On, Department of Mathematics, Duke University

if nargout==3
    err = MException('OutputChk:OutOfRange', 'You may not call this function with 3 output variables!  Use 1, 2, or 4!');
    throw(err);
end

if size(pc,2)>size(pc,1)
    disp('WARNING: matrix has more rows than columns.  Do you want to transpose it?')
end

if isempty(pc) 

    err = MException('ResultChk:OutOfRange', 'Point cloud is empty!  Perhaps you forgot to transpose an array somewhere?');
    throw(err);
end

tda = initializejavaTDA();  % create a new Java TDA object

param2 = strcat( 'distanceBoundOnEdges=', num2str( distanceBoundOnEdges ) ); % set distancebound as string

tda.RCA1( { 'settingsFile=tda/data/rca1.settings.txt','supplyDataAs=pointCloud', param2 }, pc);  % compute 1-D persistence, using supplied parameters

I0 = tda.getResults(0).getIntervals;  % 0-D intervals
I1 = tda.getResults(1).getIntervals;  % 1-D intervals
edges = tda.getResults(2); % number of edges
redcols = tda.getResults(3); % number of columns in the reduction matrix

varargout{1}=I1;

if nargout>1
    varargout{2}=I0;
end

if nargout==4
    varargout{3}=edges;
    varargout{4}=redcols;
end
end

