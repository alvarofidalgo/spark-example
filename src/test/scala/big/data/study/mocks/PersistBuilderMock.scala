package big.data.study.mocks

import big.data.study.persist.{PersistBuilder, Persist}
import org.scalatest.mock.MockitoSugar
import org.mockito.Mockito.when


trait PersistBuilderMock extends MockitoSugar {

  def mockBuilder (message:String,persist:Persist): PersistBuilder = {
     val persistBuilder = mock[PersistBuilder]
     when(persistBuilder.build(message)).thenReturn(persist)
    persistBuilder
  }

}
