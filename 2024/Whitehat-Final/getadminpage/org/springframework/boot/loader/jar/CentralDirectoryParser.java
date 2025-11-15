package org.springframework.boot.loader.jar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.boot.loader.data.RandomAccessData;

class CentralDirectoryParser {
   private static final int CENTRAL_DIRECTORY_HEADER_BASE_SIZE = 46;
   private final List<CentralDirectoryVisitor> visitors = new ArrayList();

   <T extends CentralDirectoryVisitor> T addVisitor(T visitor) {
      this.visitors.add(visitor);
      return visitor;
   }

   RandomAccessData parse(RandomAccessData data, boolean skipPrefixBytes) throws IOException {
      CentralDirectoryEndRecord endRecord = new CentralDirectoryEndRecord(data);
      if (skipPrefixBytes) {
         data = this.getArchiveData(endRecord, data);
      }

      RandomAccessData centralDirectoryData = endRecord.getCentralDirectory(data);
      this.visitStart(endRecord, centralDirectoryData);
      this.parseEntries(endRecord, centralDirectoryData);
      this.visitEnd();
      return data;
   }

   private void parseEntries(CentralDirectoryEndRecord endRecord, RandomAccessData centralDirectoryData) throws IOException {
      byte[] bytes = centralDirectoryData.read(0L, centralDirectoryData.getSize());
      CentralDirectoryFileHeader fileHeader = new CentralDirectoryFileHeader();
      int dataOffset = 0;

      for(int i = 0; i < endRecord.getNumberOfRecords(); ++i) {
         fileHeader.load(bytes, dataOffset, (RandomAccessData)null, 0L, (JarEntryFilter)null);
         this.visitFileHeader((long)dataOffset, fileHeader);
         dataOffset += 46 + fileHeader.getName().length() + fileHeader.getComment().length() + fileHeader.getExtra().length;
      }

   }

   private RandomAccessData getArchiveData(CentralDirectoryEndRecord endRecord, RandomAccessData data) {
      long offset = endRecord.getStartOfArchive(data);
      return offset == 0L ? data : data.getSubsection(offset, data.getSize() - offset);
   }

   private void visitStart(CentralDirectoryEndRecord endRecord, RandomAccessData centralDirectoryData) {
      Iterator var3 = this.visitors.iterator();

      while(var3.hasNext()) {
         CentralDirectoryVisitor visitor = (CentralDirectoryVisitor)var3.next();
         visitor.visitStart(endRecord, centralDirectoryData);
      }

   }

   private void visitFileHeader(long dataOffset, CentralDirectoryFileHeader fileHeader) {
      Iterator var4 = this.visitors.iterator();

      while(var4.hasNext()) {
         CentralDirectoryVisitor visitor = (CentralDirectoryVisitor)var4.next();
         visitor.visitFileHeader(fileHeader, dataOffset);
      }

   }

   private void visitEnd() {
      Iterator var1 = this.visitors.iterator();

      while(var1.hasNext()) {
         CentralDirectoryVisitor visitor = (CentralDirectoryVisitor)var1.next();
         visitor.visitEnd();
      }

   }
}
